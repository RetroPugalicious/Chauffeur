from flask_restful import Resource

class PageCall(Resource):

    def __init__(self, **kwargs):
        self.rooms = kwargs['rooms']

    def get(self, room, page):
        rooms = self.rooms
        room_content = rooms[room]
        total_results = room_content['api_results']
        page_content = total_results[str(page)]
        return page_content

