from flask_restful import Resource


class GetCalls(Resource):

    def __init__(self, **kwargs):
        self.rooms = kwargs['rooms']

    def get(self, room):
        """

        :return:
        """
        room_info = self.rooms[room]
        return room_info['api_results'] != {}
