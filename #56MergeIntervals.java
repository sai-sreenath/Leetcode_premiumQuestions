class Solution {
    public int[][] merge(int[][] intervals) {
        PriorityQueue<int[]>queue = new PriorityQueue<int[]>(intervals.length, new Comparator<int[]>(){
            public int compare(int[] a, int[] b)
            {
                return a[0]-b[0];
            }
        });
        
        for(int i=0;i<intervals.length;i++)
            queue.add(intervals[i]);
        
        List<int[]> result = new ArrayList<>();
        
        while(!queue.isEmpty())
        {
            int[] temp1 = queue.poll();
            int[] temp2 = queue.poll();
            
            if(temp2!=null && temp2[0]<=temp1[1])
            {
                temp1[1] = Math.max(temp1[1], temp2[1]);
                temp1[0] = Math.min(temp1[0], temp2[0]);
                queue.add(temp1);
            }
            else
            {
                if(temp2!=null)
                    queue.add(temp2);
                
                result.add(temp1);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
