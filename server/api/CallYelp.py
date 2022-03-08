import requests
from flask_restful import Resource

business_data = ""


class CallYelp(Resource):

    def __init__(self, **kwargs):
        self.rooms = kwargs['rooms']

    def get(self, room, location, search_type, radius):
        """
        Given the parameters of a
        request, will make said
        request
        :return:
        """

        room_info = self.rooms[room]

        room_info['params'] = {'categories': search_type,
                               'limit': 50,
                               'radius': radius,
                               'location': location}

        with open('api.key') as f:
            API_KEY = f.readlines()
        with open('client.ID') as f:
            client_id = f.readlines()

        # define endpoint and header
        ENDPOINT = 'https://api.yelp.com/v3/businesses/search'
        HEADERS = {'Authorization': 'bearer ' + API_KEY[0]}

        # make a request to yelp api
        response = requests.get(url=ENDPOINT, params=room_info['params'], headers=HEADERS)

        # convert json string to dictionary
        business_data = response.json()

        businesses = business_data['businesses']
        business_list = {}

        i = 0
        for business in businesses:
            business_list[i] = business
            i = i + 1

        room_info['api_results'] = business_list

        # print(business_data)
        # for biz in business_data['businesses']:
        #     print(biz['name'])

        selection = list(business_data['businesses'])

        i = 0
        for user_selection in selection:

            room_info['user_responses'][user_selection['id']] = 0

        return room_info['api_results']
