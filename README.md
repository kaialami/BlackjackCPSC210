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
User events:
- User hit (add X to Y)  
- Check user score (Score is a value dependent on current Y)       


Sample event log after two rounds of play where the user clicked the hit button twice and the check score button three times:

Event log:  
Wed Mar 30 20:39:05 PDT 2022  
User hit

Wed Mar 30 20:39:07 PDT 2022  
Check user score

Wed Mar 30 20:39:09 PDT 2022  
Check user score

Wed Mar 30 20:39:23 PDT 2022  
User hit

Wed Mar 30 20:39:32 PDT 2022  
Check user score


****
## Phase 4: Task 3
From building my UML diagram, I noticed how many classes have associations for User and Deck, 
when in the actual game there is only one User and Dealer. If I was not careful, it could be possible to have two separate 
instances of User or Dealer. I would probably refactor this so that it reduces this coupling. For example, I would probably
center my three main fields for the game (User, Dealer, Deck) in GameState, and then access them from GameState whenever 
I needed them. This way I don't need to worry if, for example, GamePanel had the same User field as BlackjackPlayer.
It would also clean up my UML by reducing coupling.

I think another change I'd like to make is to split up BlackjackPlayer's functionality into other classes. 
As it is, this class manages both the application window and the actual game functionality (like user input), so it's very long with a lot of
helper methods. I think it would be possible (and fairly easy enough) to refactor this class so that the parts that manage the 
window are separate from the parts that manage how the game runs. I could even refactor the methods that manage persistence 
in BlackjackPlayer into another class made solely for that purpose.