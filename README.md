# Overview
- This is the API service for the game (Unity) and the webstie to get the data or get other information 
that is needed to function

# IMPORTANT NOTE
- Time format for the race pass in as integer of amount of milisecond

# Unity Functionality

## Game Loging Service
- Loging each game result
    url: "BASELINK/gameservice/gamelog"
    Request body: RaceLog Template    

- Fetch the game log result by the raceID
    url: "BASELINK/gameservice/gamelog/{rid}"
    Where rid is raceID

- Fetch the game log result by the playerID
    url: "BASELINK/gameservice/gamelog/{pid}"
    Where pid is player ID

## Player Information Service
- Getting player information through uid
    url: "BASELINK/gameservice/playerlog/login/uid/{uid}"
    Where uid id university ID

- Getting player information through pid
    url: "BASELINK/gameservice/playerlog/login/pid/{pid}"
    Where pid id player ID

- Getting player information by username and password

    url: "BASELINK/gameservice/playerlog/login/{username}/{password}" 
    Where username is player username
    Where password is the passwowrd
    
- Checking email avalability
    url: "BASELINK/gameservice/playerlog/{email}"
    Where email is player email

- Create a new user NO UID template
    url: "BASELINK/gameservice/playerlog/create"
    Request Body: NO UID template

- Create a new user with UID
    url: "BASELINK/gameservice/playerlog/create/uid"
    Request Body: UID template

# Web Functionality

## Account Service 

- Getting player information through pid (GET)
    url: "BASELINK/webservice/playerinfo/login/pid/{pid}"
    Where pid id player ID

- Getting player information by username and password (GET)
    url: "BASELINK/webservice/playerinfo/login/{username}/{password}" 
    Where username is player username
    Where password is the passwowrd
    
- Checking email avalability (GET)
    url: "BASELINK/webservice/playerinfo/{email}"
    Where email is player email

- Get Player performance with trackID (GET)
    url: "BASELINK/webservice/playerinfo/{pid}/{tid}"
    Where pid is player ID
    Where tid is trackID

- Get recent races (GET)
    url: "BASELINK/webservice/playerinfo/{pid}"

- Create a new user NO UID template (POST)
    url: "BASELINK/webservice/playerinfo/create"
    Request Body: NO UID template
    
- Create a new user with UID (POST)
    url: "BASELINK/webservice/playerinfo/create/uid"
    Request Body: UID template



## Leaderboard Service
- Looking up top map record for requested map




## Request Body Template

UID Template:
    {
    "email": "string",
    "username": "string",
    "password": "string",
    "uid": 0
    }

NO UID:
    {
    "email": "string",
    "username": "string",
    "password": "string"
    }

Race Log:
    {
    "pid": "string",
    "raceStartTime": "2025-06-16T19:59:19.682Z",
    "raceTime": 0,
    "racePos": 0,
    "mapRaced": 0,
    "characterUsed": 0,
    "collisionWithPlayer": 0,
    "collisionWithWall": 0,
    "felloffmap": 0,
    "boostStat": {
        "speedBoost1": 0,
        "speedBoost2": 0,
        "speedBoost3": 0
    },
    "offenseStat": {
        "puck1": 0,
        "puck2": 0
    },
    "trapUsage": {
        "oilSpill1": 0,
        "oilSpill2": 0
    }
    }