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

        pages = {
            '1': {'0': business_data['businesses'][0], '1': business_data['businesses'][1], '2': business_data['businesses'][2], '3': business_data['businesses'][3],
                  '4': business_data['businesses'][4], '5': business_data['businesses'][5], '6': business_data['businesses'][6], '7': business_data['businesses'][7],
                  '8': business_data['businesses'][8], '9': business_data['businesses'][9]},
            '2': {'0': business_data['businesses'][10], '1': business_data['businesses'][11], '2': business_data['businesses'][12], '3': business_data['businesses'][13],
                  '4': business_data['businesses'][14], '5': business_data['businesses'][15], '6': business_data['businesses'][16], '7': business_data['businesses'][17],
                  '8': business_data['businesses'][18], '9': business_data['businesses'][19]},
            '3': {'0': business_data['businesses'][20], '1': business_data['businesses'][21], '2': business_data['businesses'][22], '3': business_data['businesses'][23],
                  '4': business_data['businesses'][24], '5': business_data['businesses'][25], '6': business_data['businesses'][26], '7': business_data['businesses'][27],
                  '8': business_data['businesses'][28], '9': business_data['businesses'][29]},
            '4': {'0': business_data['businesses'][30], '1': business_data['businesses'][31], '2': business_data['businesses'][32], '3': business_data['businesses'][33],
                  '4': business_data['businesses'][34], '5': business_data['businesses'][35], '6': business_data['businesses'][36], '7': business_data['businesses'][37],
                  '8': business_data['businesses'][38], '9': business_data['businesses'][39]},
            '5': {'0': business_data['businesses'][40], '1': business_data['businesses'][41], '2': business_data['businesses'][42], '3': business_data['businesses'][43],
                  '4': business_data['businesses'][44], '5': business_data['businesses'][45], '6': business_data['businesses'][46], '7': business_data['businesses'][47],
                  '8': business_data['businesses'][48], '9': business_data['businesses'][49]}
        }

        room_info['api_results'] = pages

        # print(business_data)
        # for biz in business_data['businesses']:
        #     print(biz['name'])

        return room_info['api_results']
