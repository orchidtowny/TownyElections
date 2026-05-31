# TownyElections

Election system for Towny designed around my idea for Residential.

Nations and Towns may enable elections in three modes:
- Leader: Government leader (king or mayor) calls an election
- Scheduled: An election starts after x amount of Towny days

Once an election has at least one candidate, voting can begin. If no candidates enroll within the election
period then the election is canceled. Votes can be changed until the election ends.

Voters will be able to rank candidates or omit them completely. If their first candidate drops out or loses, their vote 
goes to their next candidate and the votes are recalculated.

Ranking works with a point system. The number of points a voter can use depends on how many candidates there are. If a 
voter ranks only 3 out of 10 candidates, then their #1 pick gets 7 points, their #2 pick gets 2 points, and their #3 pick
gets 1 point.

At least 25% of the population has to vote for the election results to go into effect.

## Planned Features

Multiple election cycle modes:
- Petition: If 25% of residents vote for an election to happen within 14 days, an election begins.