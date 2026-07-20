class Solution {
    static HashMap<String,Boolean> visited;
    public void findSecretWord(String[] wordlist, Master master) {
        visited=new HashMap<>();
        ArrayList<String> word_list=new ArrayList<>();
        for(int i=0;i<wordlist.length;i++)
        {
            word_list.add(wordlist[i]);
        }
        //Shuffling the original list to randomly pick a word
        Collections.shuffle(word_list);
        for(String word:word_list)
        {
            // This is one of the possible answers as it's unvisited
            if(visited.get(word)==null)
            {
                //We are doing a query here
                int char_count=master.guess(word);
                visited.put(word,true);
                if(char_count==0)
                {
                    /*
                        As no character of this word matches with the secret word
                        Now if any other word's character matches with this current word's character then it can't possibly be the answer
                    */
                    remove_completely(word,word_list);
                    continue;
                }
                if(char_count==6)
                {
                    break;
                }
                /*
                    Now if 'x' characters have matched with the secret word any other word
                    having any less matched character with this current word can't possibly be the answer
                */
                remove(word,word_list,char_count);
            }
        }
    }
    public static void remove_completely(String source,ArrayList<String> word_list)
    {
        for(String word:word_list)
        {
            if(word.equals(source))
            {
                continue;
            }
            int curr_match_length=check_with_current_word(source,word);
            if(curr_match_length>0)
            {
                //Then mark this word as visited as this can't possibly be the answer
                visited.put(word,true);
            }
        }
    }
    public static void remove(String source,ArrayList<String> word_list,int matching_char_count_with_secret_word)
    {
        for(String word:word_list)
        {
            if(word.equals(source))
            {
                continue;
            }
            int curr_match_length=check_with_current_word(source,word);
            if(curr_match_length<matching_char_count_with_secret_word)
            {
                //Then mark this word as visited as this can't possibly be the answer
                visited.put(word,true);
            }
        }
    }
    public static int check_with_current_word(String source,String word)
    {
        int count=0;
        for(int i=0;i<word.length();i++)
        {
            if(word.charAt(i)==source.charAt(i))
            {
                count++;
            }
        }
        return count;
    }
}