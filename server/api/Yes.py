from flask_restful import Resource

class Yes(Resource):

    def get(self, room, user):
        """
        Given a room and user, will
        register a yes for this restaurant
        from this user
        :return:
        """
        return dict({"leader: ": 0})
