from flask_restful import Resource

class Yes(Resource):

    def __init__(self, **kwargs):
        self.rooms = kwargs['rooms']

    def get(self, room, business_id):
        """
        Given a room and user, will
        register a yes for this restaurant
        from this user
        :return:
        """
        room_info = self.rooms[room]
        user_responses = room_info['user_responses']
        yeses = user_responses[business_id]
        yeses = yeses + 1

        if yeses != room_info['user_count']:
            user_responses[business_id] = yeses
            return False

        return True
