import os
from datetime import date, timedelta
from dotenv import load_dotenv
from pymongo import MongoClient


load_dotenv("../.env")
MONGO_URI = os.getenv("MONGO_URI", "mongodb://localhost:27017/library")
client = MongoClient(MONGO_URI)
db = client.get_default_database()


TODAY = date.today()


# Find loans due in 2 days and overdue
upcoming = list(db.loans.find({"returned": False, "dueOn": {"$lte": (TODAY + timedelta(days=2)).isoformat() }}))
overdue = list(db.loans.find({"returned": False, "dueOn": {"$lt": TODAY.isoformat() }}))


members = { m["_id"]: m for m in db.members.find({}) }
books = { b["_id"]: b for b in db.books.find({}) }


def pretty(loan):
m = members.get(loan["memberId"], {})
b = books.get(loan["bookId"], {})
return f"{m.get('name','(unknown)')} — {b.get('title','(unknown)')} — due {loan['dueOn']}"


print("UPCOMING DUE (<= 2 days):")
for l in upcoming:
print(" - ", pretty(l))


print("\nOVERDUE:")
for l in overdue:
print(" - ", pretty(l))
