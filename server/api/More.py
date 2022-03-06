from flask_restful import Resource

class More(Resource):

    def get(self, room, user):
        """
        Given a room and user, will
        decide who needs more options
        :return:
        """
        return dict({"leader: ": 0})
