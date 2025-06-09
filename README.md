# Overview
- This is the API service for the game (Unity) and the webstie to get the data or get other information 
that is needed to function

# IMPORTANT NOTE
- Time format for the race pass in as integer of amount of milisecond

# Unity Functionality

## Game Loging Service
- Loging each game result
- Fetch the game log result by the PID
- Fetch the game log result by the raceID

## Player Information Service
- Getting player information through pid
- Getting player by username
- Create a new user NO UID
- Create a new user with UID

# Web Functionality

## Account Service 
- Create new account no UID
- Create new account with UID
- Return simple player info with Username + PW
- Return simple player info with pid

## Leaderboard Service
- Looking up top map record for requested map

