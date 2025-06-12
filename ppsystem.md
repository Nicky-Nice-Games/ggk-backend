# The main scoring system 

## Introduction (Each Map Portion)

The score system will take into account of all of the provided statistic of the race and try to calculate the performance of a certain race.
Here is all of the current race attribute that will taken into account for the race.

Race Score Attribute
- Track time vs expected track time - (85% of the total score)
- Placement in the race itself - (10% of the total score)
- Item Usage - (5% of the total score)
- Fall of the map (Up to 20% of total score penalty) - Flat cap at 10

## Introduction (Total Score For Profiles)

The profiles will total scoring will be taking account into the highest 5 race score for each map (in which the less point of the top 5 they will worth the less point) and take the sum for all of the map currently in play



## Total point calculation - Each Race
The score of each race will be the sum of the max at 10000 point and the Item Usage bonus scoring multiply by the track difficulty multiplier 

### Track time portion (Accounting up to 8500 of total point)
Calculated by 

Time Portion Scoring  = (Perfection assumption time / Actual time being recorded) * 8500 

OR 8500 if the player drive faster than the perfect run score

Example:

Bobby time was 386998 milisecond map for a map that expecting to complete in 250000 milisecond

Then his score will be: 

Time Portion Scoring = 250000 / 386998 * 8500 = 5490.98 point for his score portion

If say Lisa finish the race in only 246927 milisecond, her Time portion Scoring for that race will be automatically be 8500 by default

### Placement Portion (Accounting up to 1000 of total point)
Calculated by

Placement Scoring = (Total player - Current Placement + 1) / Total Player * 1000

Example:
If Lisa got first in the race of the race of 12 people she would get

Placement Scoring = (12 - 1 + 1) / 12 * 1000 = 1000 point (perfect score)

Another race with Bobby get 4 in the race of 11 people he would get

Placement Scoring = (11 - 4 + 1) / 11 * 1000 = which would be around 727.272 points for his placement reward

### Item Usage Portion (Accounting up to 500 of total point)
Calculated by

Item Usage Scoring = (Actual item usage/ Expected Item Usage) * 500 

Or 500 if the player use more item than track expectation


Example: 

So if for a certain track expecting you to collect 5 item on the race and Bobby only collect 3 item throughout the race. He will have

Item Usage Scoring = (3 / 5) * 500 = 300 for his item usage

Another example with Lisa that she use 6 item during the entire race. She will automatically get 500 as her item usage score

### Total Score and penalty
Calculated by

Total Score = Current total score after adding all of the attribute * (1 - (0.02 * MIN (Times fall of the map or 10))) * map difficulty

Example:
So if you utilizing all of the previous example example of Bobby (which will say fall of the map 4 times during the race) and Lisa (fall of the map once). And the map have the difficulty multiplier of 2.25. Then we will have

Bobby Score = (5490.90 + 727.27 + 300) * (1 - 0.02 * 4) * 2.25 = 13,492 total points
Lisa Score = (8500 + 1000 + 500) * (1 - 0.02 * 1) * 2.25 = 22,050 total points (RIP Lisa perfect run for the day)

## Total Point for Profiles

For each map the top 5 race for a certain they will be value at:

Map effective point = map score * 0.9 ^ (n-1). With n is the nth highest score of that map (upto 5)


So if we use the example of Bobby with 4 map in the current effective play, with the following data

Map 1 scores: 19375, 18775, 18393, 14856, 12937
Map 2 scores: 33392, 32024, 30283, 29464, 28774
Map 3 scores: 46792, 45992, 45827, 44259, 43027
Map 4 scores: 67284, 65917, 63957, 62384, 61028

Then his effective point for each map will be (will be 0 if there are not enough completion run)
Map 1 Effective point: 19375, 16897.5, 14898.33, 10830.024, 8487.9657
Map 2 Effective point: 33392, 28821.6, 24529.23, 21479.256, 18878.6214
Map 3 Effective point: 46792, 41392.8, 37119.87, 32264.811, 28230.0147
Map 4 Effective point: 67284, 59325.3, 51805.17, 45477.936, 40040.4708

Then his total point for his profiles will be (sum of all of the effective score) 647321.8996
