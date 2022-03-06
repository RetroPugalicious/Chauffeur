from flask_restful import Resource

class Users(Resource):

    def __init__(self, **kwargs):
        self.rooms = kwargs['rooms']

    def get(self, room):
        """
        Given a room, will send the
        names of the users
        :return:
        """
        return self.rooms[room]['user_count']
