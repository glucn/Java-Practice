/**
 * @author Gary Lu
 * 
 * University of Waterloo - Intermedia Java Programming
 * Lesson 3: File Input and Output
 * Assignment
 * 
 * Read a text file(input.txt) which contains some simple program code and comments.
 * Output:
 * file1: contains all the comments that begin with '//', the slashs:
 *   (1) in the first two position of the line
 *   (2) not in the first column
 * 
 * file2: contains all the other lines
 * 
 */

package fileInputOutput;

import java.util.*;
import java.io.*;

class Line{
	String content;
	int lineNo;
	
	Line(String content, int lineNo){
		this.content = content;
		this.lineNo = lineNo;
	}
}

class CommentLines{
	ArrayList<Line> pureComments; // comments which start from the beginning of lines
	ArrayList<Line> detailComments; // comments which do not start from the beginning of lines
	
	/*
	 * Output all the comment lines into a String
	 */
	String toFileString() {
		StringBuilder result = new StringBuilder("Pure Comment Lines:\n");
		for(Line l: pureComments) {result.append(Integer.toString(l.lineNo) + ":" + l.content + "\n");}
		result.append("\nDetail Comment Lines:\n");
		for(Line l: detailComments)  {result.append(Integer.toString(l.lineNo) + ":" + l.content + "\n");}
		return result.toString();
	}
	
	/*
	 * Add comment lines into pureComments or detailComments
	 * content - 
	 * lineNo -
	 * type - 0:pureComment, 1:detailComments
	 */
	void addLine(String content, int lineNo, int type) {
		if(type == 0) pureComments.add(new Line(content, lineNo));
		else detailComments.add(new Line(content, lineNo));
	}
	
	CommentLines(){
		pureComments = new ArrayList<Line>();
		detailComments = new ArrayList<Line>();
	}
}

class NonCommentLines{
	ArrayList<Line> nonComments;
	
	/*
	 * Output all the comment lines into a String
	 */
	String toFileString() {
		StringBuilder result = new StringBuilder("Non-Comment Lines:\n");
		for(Line l: nonComments) {result.append(Integer.toString(l.lineNo) + ":" + l.content + "\n");}
		return result.toString();
	}
	
	/*
	 * Add comment lines into nonComments
	 * content - 
	 * lineNo -
	 */
	void addLine(String content, int lineNo) {
		nonComments.add(new Line(content, lineNo));
	}
	
	NonCommentLines(){
		nonComments = new ArrayList<Line>();
	}
}

public class FileParser {

	public static void main(String[] args) {
		CommentLines cl = new CommentLines();
		NonCommentLines ncl = new NonCommentLines();
		Scanner scan = null;
		PrintStream outputFileComment = null;
		PrintStream outputFileNonComment = null;
		int lineNo = 0;
		try {
			//read input file
			System.out.println(new File(".").getAbsoluteFile());
			scan = new Scanner(new File("FileInputOutput_input.txt"));
			while(scan.hasNextLine()) {
				++lineNo;
				String lineContent = scan.nextLine();
				int n = lineContent.indexOf("//");
				if(n == 0) {
					cl.addLine(lineContent, lineNo, 0);
				}
				else if(n!=-1) {
					cl.addLine(lineContent, lineNo, 1);
					if(!lineContent.substring(0, n-1).trim().equals(""))
						ncl.addLine(lineContent.substring(0, n-1), lineNo);
				}
				else {
					if(!lineContent.trim().equals(""))
						ncl.addLine(lineContent, lineNo);
				}
			}
			
			//write output file
			outputFileComment = new PrintStream("FileInputOutput_output_comments.txt");
			outputFileComment.print(cl.toFileString());
			
			outputFileNonComment = new PrintStream("FileInputOutput_output_noncomments.txt");
			outputFileNonComment.print(ncl.toFileString());

		}
		catch(IOException e) {
			System.out.println(e);
		}
		catch(NullPointerException e) {
			System.out.println(e);
		}
		finally{
			if(scan!=null) scan.close();
			outputFileComment.close();
			outputFileNonComment.close();
		}

	}

}
