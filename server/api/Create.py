import string
import random
from flask_restful import Resource


class Create(Resource):

    def __init__(self, **kwargs):
        self.rooms = kwargs['rooms']

    def get(self):
        """
        Will create a new room
        :return:
        """

        roomname = ''.join(random.choices(string.ascii_uppercase, k=6))
        new_room = {'room_id': roomname,
                    'user_count': 0,
                    'params': {},
                    'api_results': {},
                    'user_responses': {}}

        self.rooms[roomname] = new_room

        return roomname
