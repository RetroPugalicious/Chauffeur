from flask_restful import Resource


class Leader(Resource):

    def __init__(self, **kwargs):
        self.rooms = kwargs['rooms']

    def get(self, room):
        """
        Given a room, will request
        the name of the room leader
        :return:
        """
        return dict({"leader: ": 0})
