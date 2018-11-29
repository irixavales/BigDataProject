from twitter import OAuth, TwitterStream, Twitter
from credentials import credentials as cred

auth = OAuth(cred['token'], cred['token_secret'], cred['consumer_key'], cred['consumer_secret'])

stream = TwitterStream(domain='stream.twitter.com', auth=auth)

for tweet in stream.statuses.sample():
    print(tweet)
