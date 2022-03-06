from flask_restful import Resource

class AddUser(Resource):

    def __init__(self, **kwargs):
        self.rooms = kwargs['rooms']

    def get(self, room):

        room_info = self.rooms[room]

        room_info['user_count'] = room_info['user_count'] + 1
        return room_info['user_count'] - 1
