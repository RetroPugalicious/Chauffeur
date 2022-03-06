import requests
from flask_restful import Resource

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

        room_info['params'] = {'type': search_type,
                       'limit': 50,
                       'radius': radius,
                       'location': location}


        # get our keys
        with open('../api.key') as f:
            API_KEY = f.readlines()
        with open('../client.ID') as f:
            client_id = f.readlines()

        # define endpoint and header
        ENDPOINT = 'https://api.yelp.com/v3/businesses/search'
        HEADERS = {'Authorization': 'bearer ' + API_KEY[0]}

        # make a request to yelp api
        response = requests.get(url=ENDPOINT, params=room_info['params'], headers=HEADERS)

        # convert json string to dictionary
        business_data = response.json()

        # print(business_data)
        for biz in business_data['businesses']:
            print(biz)

        room_info['api_results'] = business_data

        return room_info['api_results']
