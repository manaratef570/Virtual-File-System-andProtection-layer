/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualfilesystem;

import java.util.ArrayList;

/**
 *
 * @author zyad
 */
public class Directory {
    
        private String directoryPath;
	private ArrayList<File> files= new ArrayList<>();
	private ArrayList<Directory> subDirectories = new ArrayList<>();
	private boolean deleted = false;
        private ArrayList<User> users= new ArrayList<>();
        private ArrayList<String> accsess= new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<String> getAccsess() {
        return accsess;
    }

    public void setAccsess(ArrayList<String> accsess) {
        this.accsess = accsess;
    }
        
        

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public ArrayList<Directory> getSubDirectories() {
        return subDirectories;
    }

    public void setSubDirectories(ArrayList<Directory> subDirectories) {
        this.subDirectories = subDirectories;
    }
        

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    //root/zyad/manar    
        
    
    void printDirectoryStructure(Directory curr,String name){
        
    System.out.println(name+curr.getDirectoryPath());
    name+=curr.getDirectoryPath();
   for(int i=0;i<curr.getFiles().size();i++){
       System.out.println(name+'/'+curr.getFiles().get(i).getFilePath());
   }
        
   for(int i=0;i<curr.getSubDirectories().size();i++){
   
       printDirectoryStructure(curr.getSubDirectories().get(i),name+'/');
   
   }
   
   
   
   }  
           
           
     }