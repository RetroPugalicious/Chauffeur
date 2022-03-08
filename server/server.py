from flask import Flask
from flask_restful import Api

from api.Create import Create
from api.Leader import Leader
from api.Users import Users
from api.CallYelp import CallYelp
from api.More import More
from api.Yes import Yes
from api.AddUser import AddUser
from api.PageCall import PageCall
from api.GetCalls import GetCalls
from api.HelloWorld import HelloWorld

app = Flask(__name__)
api = Api(app)
room_list = {}

api.add_resource(HelloWorld, '/')
api.add_resource(Leader, '/leader/<string:room>', resource_class_kwargs={'rooms': room_list})
api.add_resource(Users, '/users/<string:room>', resource_class_kwargs={'rooms':  room_list})
api.add_resource(CallYelp, '/call/<string:room>/<string:location>/<string:search_type>/<int:radius>/', resource_class_kwargs={'rooms':  room_list})
api.add_resource(More, '/more/<string:room>/<int:user>', resource_class_kwargs={'rooms':  room_list})
api.add_resource(Yes, '/yes/<string:room>/<string:business_id>', resource_class_kwargs={'rooms':  room_list})
api.add_resource(Create, '/create/', resource_class_kwargs={'rooms':  room_list})
api.add_resource(AddUser, '/join/<string:room>', resource_class_kwargs={'rooms':  room_list})
api.add_resource(PageCall, '/display/<string:room>', resource_class_kwargs={'rooms':  room_list})
api.add_resource(GetCalls, '/getCalls/<string:room>', resource_class_kwargs={'rooms':  room_list})

if __name__ == '__main__':
    app.run(debug=False, host="0.0.0.0")
