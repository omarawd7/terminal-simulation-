
package com.company;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Terminal {
	public class Parser {
	    String commandName;
	    String[] args;
	    String[] arg=new String[5];
	    String y;
	    String f;
	    static int index=0;
	    static int in=0;
	    boolean check=false;
	    static ArrayList<String> comandslist = new ArrayList<String>();
	    public Parser(){

	        comandslist.add("echo");comandslist.add("pwd");comandslist.add("exit");comandslist.add("cd");comandslist.add("cd..");comandslist.add("ls");comandslist.add("ls-r");comandslist.add("mkdir");comandslist.add("rmdir");comandslist.add("touch");comandslist.add("cp");comandslist.add("cp-r");comandslist.add("rm");comandslist.add("cat");

	        commandName=" ";

	        for(int i=0;i<arg.length;i++) {
	            arg[i]="0";
	        }
	    }
	    public boolean parse (String input){

	        index=in;
	        String[] arr = input.split(" ");
	        f=arr[0];
	        if(arr.length==1) {
	            check=true;
	            setCommandName(arr[0]);
	            index=0;
	            return true;

	        }
	        if(arr.length>2)
	        {
	            for(int i=0;i<comandslist.size();i++) {
	                if(input.contains(comandslist.get(i)) && comandslist.get(i).equals(f)) { //arglist.lastIndexOf(input) > 0 ||
	                    check=true;
	                    setCommandName(comandslist.get(i));
	                    for(int j=1;j<arr.length;j++) {
	                        y=arr[j];
	                        setArgs(y);
	                    }

	                    return true;
	                };
	            }
	            exception();
	            return false;


	        }
	        ////////////
	        else {
	            for(int i=0;i<comandslist.size();i++) {
	                if(input.contains(comandslist.get(i)) && comandslist.get(i).equals(f)) { //arglist.lastIndexOf(input) > 0 ||
	                    check=true;
	                    setCommandName(comandslist.get(i));
	                    y=comandslist.get(i)+" ";
	                    setArgs(input.replace(y,""));
	                    return true;
	                };
	            }
	            exception();
	            return false;
	        }
	    }
	    public void setArgs(String c) {

	        arg[in]=c;
	        in++;
	        index++;
	    }
	    public void setCommandName(String c) {
	        if(c==null) {
	            exception();
	            return;
	        }
	        commandName=c;
	    }

	    public String getCommandName(){
	        if(	check) {

	            return commandName ;
	        }
	        else {
	            return null;
	        }

	    }
	    public String[] getArgs(){
	        if(check) {
	            args=arg;
	            in=0;
	            return args;
	        }
	        else {
	            return null;		}

	    }
	    public String printArgs(){
	        if(	check) {
	            args=arg;
	            for(int i=0;i<index;i++) {
	                System.out.print( args[i]+" ");
	            }

	        }
	        else {
	            System.out.println("try again");
	        }
	        return args[0];

	    }
	    public void exception(){
	        System.out.println("Wrong Input");
	        check=false;
	    }
	    public static int getIndex() {

	        return index;
	    }

	    /////////////////////////////////////////////////

	}

    Parser parser;
    static String currentPath=System.getProperty("user.home");
    static String  currentDirectory=System.getProperty("user.home");
    static int index=0;
    static int ind;
    static private String homeDir  = "D://";
    public Terminal(){

    }
    public void echo(String [] input) throws IOException{
        ind=Parser.getIndex();
        for(int i=0;i<ind;i++) {
            System.out.print(input[i]+" ");
        }
        System.out.println(" ");
    }
    public void pwd() throws IOException {
        System.out.println( System.getProperty("user.dir"));
    }
    public void cd_() throws IOException {
        String s="\\";
        String w;
        int r;
        if(currentPath.length()<3) {
            System.out.println( currentPath+">");
        }
        else {
            r=currentPath.lastIndexOf(s);
            w=currentPath.substring(0,r);
            currentPath=w;
            System.out.println( w+">");
        }
    }
    public void cd() throws IOException {

        System.out.println( currentPath+">");
    }
    public void cd(String [] d) throws IOException {
        String w;
        int r;
        String k;
        String s="\\";

        if(d[0].contains("C:")|| d[0].contains("D:") || d[0].contains("G:")) {
            File f =new File (d[0]);
            if (f.exists()) {
                currentPath=d[0];
                currentDirectory=d[0];
                System.out.println(d[0]+">");
            }
            else {
                System.out.println( "The system cannot find the path specified.");
            }
        }
        else {
            k=currentDirectory+s;
            w=k+d[0];
            currentPath=w;
            File fl =new File (w);
            if (fl.exists()) {
                System.out.println( w+">");
            }
            else {
                System.out.println( "The system cannot find the path specified.");
            }
        }
        // }

    }
    public void ls() throws IOException{
        String x=currentPath.substring(2,currentPath.length());
        String[] arr = x.split("\\\\");
        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i]+" ");
        System.out.println("");
    }
    public void ls_r() throws IOException{
        String x=currentPath.substring(2,currentPath.length());
        String[] arr = x.split("\\\\");
        Stream.of(arr)
                .sorted()
                .toArray(String[]::new);

        for (int i = arr.length-1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
    public void touch(String[] path) throws IOException {
        //Takes the path of the file and creates this file.
        File file=new File(path[0]);
        file.createNewFile();
        file.renameTo(file);
    }
    public void cp(String[] files) throws IOException{
        //Takes Two files and copies the first onto the second.
        FileReader FR=null;
        FileWriter FW=null;
        try {
            File firstOne=new File(files[0]);
            File secondOne=new File(files[1]);
            //Files.copy(firstOne.toPath(),secondOne.toPath());
            FR=new FileReader(firstOne);
            FW=new FileWriter(secondOne);
            int i;
            while ((i=FR.read())!=-1){
                FW.write(i);
            }
        }catch (IOException e){
            System.out.println("there is an IOEXCEPTION");
        }finally {
            if(FR!=null){
                FR.close();
            }
            if(FW!=null){
                FW.close();
            }
        }

    }
    public void cp_r(String[] dictionaries) throws IOException{
        //Takes Two Directories and copies the first onto the second.
        File direc1=new File(dictionaries[0]);
        File direc2=new File(dictionaries[1]);
        if(direc1.exists()){
            if(direc1.isDirectory()){
                    Files.copy(direc1.toPath(),direc2.toPath());

            }
        }
    }
    /*public void mkdir (String[] folderName) throws IOException {
        for(int i=0;i<folderName.length;i++)
        {
            File d = new File(folderName[i]);
      /*  if(!d.isAbsolute())
        { d=new File(homeDir+"\\"+folderName);
        }*/
           /* if (!d.exists()) {
                // Files.createDirectory(d.toPath());
                d.mkdirs();
                System.out.println("folder created");
                break;

            } else {
                System.out.println("folder already exists");
                break;

            }
        }
    }*/
    public void mkdir(String[] FolderName){
        for (int i = 0; i < FolderName.length; i++) {
            File f ;
            if (FolderName.length == 0) {
                System.out.println("No arguments!");
            } else {
                if(FolderName[i].contains("\\")) {
                    f = new File(FolderName[i]);
                }
                else {
                    f = new File(homeDir + "\\" + FolderName[i]);
                }
                if (!f.exists()) {
                    boolean Existing = f.mkdir();
                    if (Existing) {
                        System.out.println("directory created ");
                    }
                } else {
                    System.out.println("The directory already exists");
                }
            }
        }
        return ;
    }
    public void rm (String[] filename) throws IOException
    {
        for(int i=0;i<filename.length;i++)
        {
            File d = new File(filename[i]);
              /*  if(!d.isAbsolute())
       { d=new File(homeDir+"\\"+filename[i]);
       }*/
            if (d.exists()&&d.isFile()) {
                d.delete();
                break;
            } else {
                System.out.println("Error");
                break;
            }

        }
    }
    public void rmdir (String[] path) throws IOException {
        for(int i=0;i<path.length;i++)
        {
            File f = new File(path[i]);

            if (f.isDirectory() && f.length()==0) {
                f.delete();
                break;
            }
            else if (f.isDirectory() && f.length()>0) {
                System.out.println("directory is not empty");
                break;
            }
            else if(!f.exists())
            {
                System.out.println("The Directory is not exist");
                break;

            }

        }
    }

    public void cat(String[] filenames) throws IOException {
        byte[] words;
        for (int i = 0; i < filenames.length; i++) {
            if (filenames.length == 0) {
                System.out.println("empty");
            } else {
                File f = new File(filenames[i]);

                if (!f.isAbsolute()) {
                    f = new File(homeDir + "\\" + filenames[i]);
                }
                if (f.exists() && f.isFile()) {
                    words = Files.readAllBytes(f.toPath());
                    System.out.println(words);
                } else {
                    System.out.println("error");
                    break;
                }

            }
        }
    }

    public void chooseCommandAction() throws IOException {
        parser=new Parser();
        Terminal terminal=new Terminal();
        Scanner input=new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String in=input.nextLine();
            String h="cd";
            if(in.equals(h)) {
            	cd();
            	continue;
            }
            parser.parse(in);

            if(parser.getCommandName().equals("exit")){
                terminal.exit();
            }
            switch (parser.getCommandName()) {
                case "echo":
                    terminal.echo(parser.getArgs());
                    break;
                case "pwd":
                    terminal.pwd();
                    break;
                case "cd":
                    terminal.cd(parser.getArgs());
                    break;
                case "cd..":                	
                    terminal.cd_();
                    break;
                case "ls":
                    terminal.ls();
                    break;
                case "ls-r":
                    terminal.ls_r();
                    break;
                case "touch":
                    terminal.touch(parser.getArgs());
                    break;
                case "cp":
                    terminal.cp(parser.getArgs());
                    break;
                case "cp-r":
                    terminal.cp_r(parser.getArgs());
                    break;
                case "mkdir":
                    terminal.mkdir(parser.getArgs());
                    break;
                case "rm":
                    terminal.rm(parser.getArgs());
                    break;
                case "rmdir":
                    terminal.rmdir(parser.getArgs());
                    break;
                case "cat":
                    terminal.cat(parser.getArgs());
                    break;
                default:
                    System.out.println("> Invalid Command ,please Enter another One:");
                    break;
            }
        }
    }
    public void exit() {System.exit(0);}
    
    
    public static void main(String[] args) throws IOException {
        // write your code here
        Terminal obj=new Terminal();
        obj.chooseCommandAction();
    }
}

