if __name__ == '__main__':

    # Import Modules
    import requests
    import json

    # get our keys
    with open('api.key') as f:
        API_KEY = f.readlines()
    with open('client.ID') as f:
        client_id = f.readlines()

    # define endpoint and header
    ENDPOINT = 'https://api.yelp.com/v3/businesses/search'
    HEADERS = {'Authorization': 'bearer ' + API_KEY[0]}

    # define the parameters
    PARAMETERS = {'term': 'coffee',
                  'limit': 50,
                  'radius': 10000,
                  'location': 'San Diego'}

    # make a request to yelp api
    response = requests.get(url=ENDPOINT, params=PARAMETERS, headers=HEADERS)

    # convert json string to dictionary
    business_data = response.json()

    # print(business_data)
    for biz in business_data['businesses']:
        print(biz)
