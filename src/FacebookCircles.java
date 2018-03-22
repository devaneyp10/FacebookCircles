/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author paul devaney
 *
 * @version 01/12/15 02:03:28
 */
public class FacebookCircles
{
		final int users;
		int sizeOfLargestCircle;
		int circles;
		int[] sizes;	
		int[] userArr;   

	
	  /**
	   * Constructor
	   * @param numberOfFacebookUsers : the number of users in the sample data.
	   * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
	   */
	
      public FacebookCircles(int numberOfFacebookUsers)
      {
		    users = numberOfFacebookUsers;
		    userArr = new int[users];
		    circles=numberOfFacebookUsers;
		    sizeOfLargestCircle = 1;
		    		    
		    sizes = new int [users];
		    int i = 0;
		    while(i<sizes.length)
		    {
		    	sizes[i]=1;
		    	i++;
		    }
		    
		    createUserArray();
	  
	  }
      
      /*
       * This method creates an array from 0 to users-1.
       * So that each user can be identified individually.
       */
      private void createUserArray() 
	  {
		  int i=0;
		  while(i<users)
		  {
			  userArr[i] = i;
			  i++;
		  }
	  }
      
      /*
       * This method takes an int and finds and returns
       * the user in the user array at that index.
       */
	  public int findUser(int i)
	  {
		  while(i!=userArr[i])
		  {
			  userArr[i]=userArr[userArr[i]];
			  i=userArr[i]; //user found
		  }
		  
		  return i;
	  }
	
	  /**
	   * creates a friendship connection between two users, represented by their corresponding integer ids.
	   * @param user1 : int id of first user
	   * @param user2 : int id of second  user
	   */
	  public void friends( int user1, int user2 )
	  {
		  int i = findUser(user1);
		  int j = findUser(user2);
		  
		  if (i!=j) //once they're not the same user
		  {
			  circles--;
			  if (sizes[i] >= sizes[j]) 
			  {
				  userArr[j] = i; 
				  sizes[i] += sizes[j];
				  if (sizes[i] > sizeOfLargestCircle)
					  sizeOfLargestCircle = sizes[i];
		  
			  }
			  else
			  { 
				  userArr[i] = j; 
				  sizes[j] += sizes[i]; 
				  if (sizes[j] > sizeOfLargestCircle)
					  sizeOfLargestCircle = sizes[j];
			  } 
		  }
		  return;
	  }
	  
	  /**
	   * @return the number of friend circles in the data already loaded.
	   */
	  public int numberOfCircles() 
	  {
	    return circles;
	  }
	
	  /**
	   * @return the size of the largest circle in the data already loaded.
	   */
	  public int sizeOfLargestCircle() 
	  {
		  return sizeOfLargestCircle;
	  }
	
	  /**
	   * @return the size of the median circle in the data already loaded.
	   */
	  public int sizeOfAverageCircle()
	  {
			int average;
			int sum= 0; //sum of circle sizes
			int numberOfCircles = numberOfCircles();
			
			int i = 0;
		    while(i < users) 
		    {
		    	if (userArr[i] == i) 		
		    		sum+= sizes[i]; 
		    	i++;
		    }
		    
		    average = sum/numberOfCircles;
		    
		    return average;
	  }
	
	  /**
	   * @return the size of the smallest circle in the data already loaded.
	   */
	  public int sizeOfSmallestCircle() 
	  {
	    int result = users; 
	    int i = 0;
	    while(i <users) 
	    {
	    	if (userArr[i] == i && sizes[i] < result) 
	    		result = sizes[i];
	    	i++;
	    }
	    return result;
	  }
	
	}