class Leaderboard {

    Map<Integer, Integer>scores;
    TreeMap<Integer, Integer>sortedscores;
    
    public Leaderboard() {
        this.scores = new HashMap<Integer, Integer>();
        this.sortedscores = new TreeMap<>(Collections.reverseOrder());
    }
    
    public void addScore(int playerId, int score) {
        if(!this.scores.containsKey(playerId))
        {
            this.scores.put(playerId, score);
            this.sortedscores.put(score, this.sortedscores.getOrDefault(score, 0) + 1);
        }
        else
        {
            // As player score is changing, we decrease old score count value
            int prescore = this.scores.get(playerId);
            int playerCount = this.sortedscores.get(prescore);
            
            //if no player has this score, remove it from tree
            if(playerCount==1)
                this.sortedscores.remove(prescore);
            else
                this.sortedscores.put(prescore, playerCount-1);
            
            //updated score
            int newscore = prescore+score;
            this.scores.put(playerId, newscore);
            this.sortedscores.put(newscore, this.sortedscores.getOrDefault(newscore, 0) + 1);
        }
    }
    
    public int top(int K) {
        int count=0;
        int sum = 0 ;
        
        //In-order traversal over the scores in TreeMap
        for(Map.Entry<Integer, Integer> entry: this.sortedscores.entrySet())
        {
            // Number of players that have this score
            int times = entry.getValue();
            int key = entry.getKey();
            
            for(int i=0; i<times; i++)
            {
                sum+=key;
                
                count++;

                if(count==K)
                    break;
            }
            if(count==K)
                break;
        }
        
        return sum;
        
    }
    
    public void reset(int playerId) {
        int prescore = this.scores.get(playerId);
        this.sortedscores.put(prescore, this.sortedscores.get(prescore)-1);
        
        if(this.sortedscores.get(prescore)==0)
            this.sortedscores.remove(prescore);
        
        this.scores.remove(playerId);
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */
