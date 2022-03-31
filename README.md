# Blackjack  
## Kai Alami  

****
## Project Overview
**What is blackjack?**  
Blackjack is a gambling game involving playing cards. 
In short, the goal is to achieve a hand where the score is as close to 21 without exceeding it.
More info <a href="https://en.wikipedia.org/wiki/Blackjack" target="_blank">here</a>. The rules of blackjack in this application will follow Wikipedia's definition,
with a few simplicities.

**What will this application do?**   
This application will allow a user to play blackjack against an automated dealer. 
The deck will contain the standard 52 cards and will act as a real deck of cards, meaning each card
can only appear once before the deck is reshuffled (so you can still count cards if you want).  
The user will start with a set amount of money and will place bets every round. If they run out of money, the game must be restarted. 
During their turn, the user will be given various decisions: hit, stand, and double down (available if applicable).
The dealer will stand on 17.  

**Who will use it?**  
People who want to gamble with fake points rather than their own money.

**Why is this project of interest to me?**  
I thought it was a simple idea but still challenging. I didn't want to go overboard with
the project so I think it's a good idea. It also incorporates a good amount of user input and variance.
Blackjack is also fun and I wanted to make something fun :)



****
## User Stories
As a user, I want to be able to:  
- place a bet on a round
- *hit* and receive a new card from the deck as many times as I want until I bust
- *stand* and keep my hand
- *double down* to receive a card and double my bet
- play as many rounds as I want (or can)
- quit playing after a round has finished
- have the option to save my progress (including balance and the current state of the deck) immediately after quitting out after a round
- load my saved progress when starting the application to the start of a new round (including saved balance and state of the deck)

****
## Phase 4: Task 2
Sample event log after two rounds of play where the user clicked the hit button twice and the check score button three times:

Event log:  
Wed Mar 30 17:34:13 PDT 2022  
User hit

Wed Mar 30 17:34:13 PDT 2022  
Check user score  

Wed Mar 30 17:34:13 PDT 2022  
Check user score  

Wed Mar 30 17:34:13 PDT 2022  
User hit  

Wed Mar 30 17:34:13 PDT 2022  
Check user score  
