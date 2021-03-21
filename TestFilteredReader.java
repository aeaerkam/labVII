// test all the classes...
import java.util.ArrayList;

public class TestFilteredReader
{
	public static void main (String[] args) 
	{
		String full = "addsfklösmD \n<asd>   asdasdanlşlkm<ssd>afddw<>adad >> >  <> aaasssdddee ewd24d32df3d34d ";
		String pageContents = "<p><b>(a)</b> Write a test program that will read the contents of <a href=\"http://www.cs.bilkent.edu.tr/~david/housman.txt\">this"
		+ "<p><b>(c)</b> A customer wants to be able to print the contents of&nbsp; <a href=\"http://www.cs.bilkent.edu.tr/~david/housman.htm\">this page blah avkjsdbvıdskb href=\"java.sun.com\"";
		
		System.out.println(pageContents);
		System.out.println();
			
		System.out.println(getLinks (pageContents));
	
	}
	
	
	public static String getFilteredPageContents(String full)
	{
		String filteredContent;
		String fullContent = full;
		int k = 0;
		
		filteredContent = "";
		
		for (int i = 0; i < fullContent.length (); i++)
		{
			if(fullContent.charAt (i) == '<')
			{
				if(k == 0)
					filteredContent += fullContent.substring (k, i);
				else
					filteredContent += fullContent.substring (k + 1, i);
				k = i;
		
				// Skip the part in the full html document in between the signs '<' and '>' by incrementing the nuber k
				// which was assigned the last scanned index of the fullContent of the html.
				while(fullContent.charAt (k) != '>')
				{
					k++;	
				}
				
				// Assigning i to k to skip the part between '<' and '>'.
				i = k;					
			}
			
			else if(i == fullContent.length () - 1 )
				filteredContent += fullContent.substring (k + 1, i);
		} 
		return filteredContent;	 
	}
	
	public static ArrayList getLinks(String pageContents)
	{
		ArrayList<String> links; 
	    
	    int j;
	    links = new ArrayList<String>();
	    
	    j = 0;
	    for(int i = 0; i < pageContents.length () - 5; i++)
	    {
	    	if( pageContents.charAt (i) == 'h' &&
	    	    pageContents.charAt (i + 1) == 'r' &&
	    	    pageContents.charAt (i + 2) == 'e' &&
	    	    pageContents.charAt (i + 3) == 'f' && 
	    	    pageContents.charAt (i + 4) == '=' &&
	    	    pageContents.charAt (i + 5) == '\"')
	   		{
	 			j = i + 6;
	 			if(j != pageContents.length ())
	 			{	
	 				while(pageContents.charAt (j) != '\"')
		 			{
		 				j++;
		 			}
		 			links.add(pageContents.substring (i + 6, j));
		 			
		 			i = j;
	 			}		
	 		}   	
	    }	
	    return links;
	}
	
}
