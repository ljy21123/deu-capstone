import doorDAO
import json

dao = doorDAO.DoorDAO()
dao.connect()
temp = dao.select_notify_users()
data = dao.select_door_announcement(temp[0]["id"])

door_announcement_info = json.loads(data['door_announcement_info'])
print(type(door_announcement_info))


dao.disconnect()