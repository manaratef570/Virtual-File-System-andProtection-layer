/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualfilesystem;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Manar Atef
 */
public class VirtualFileSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

      Alocation_techniques allocationTechnique = null;
      Manger manger=new Manger();
      Directory root=new Directory();
      root.setDirectoryPath("root");   
      User user=new User("admin","admin","admin");
      ArrayList<User>users= new ArrayList<>();
      users.add(user);
      User currUser=user;
      
       int disk[];  
       int size;
        Scanner input= new  Scanner(System.in);
        System.out.println("Enter disk size");
        size= input.nextInt();
        
        disk= new int[size];
       
 
        System.out.println("what is the allocation technique");
        System.out.println("1-Contiguous Allocation\n"
                           + "2-Linked Allocation\n"
                           + "3-Indexed Allocation\n");
        
        
        int type=input.nextInt();
       
      //instance from type of technique
        if(type==1){
        allocationTechnique= new ContiguousAllocation();
        
        }
        else if(type==2){
        allocationTechnique= new LinkedAllocation();
        
        }
        else if(type==3){
        
        allocationTechnique=new IndexedAllocation();
        
        }
        
        
        //load folders and files from file  
        FileReader myFile= new FileReader("save.txt");
        allocationTechnique.loadFromFile(root, myFile,disk);
        
        //load users from file  
        FileReader myUsersReader= new FileReader("users.txt");
        manger.loadUsers(myUsersReader, users);
        
        //load access from file
        FileReader myUsersAccess= new FileReader("access.txt");
        manger.loadAccess(myUsersAccess, root, users);

        
        
        
        
        String command;
        
        while(true){
            
            System.out.print("\n$");
            command=input.next();
            
            
        if(command.equalsIgnoreCase("CreateFile")){
            String name=input.next();
            int fileSize=input.nextInt();
            String acc=manger.chcekAcc(root, currUser, name);
            if(acc.equals("11")||acc.equals("10")||currUser.getType().equals("admin")){
            manger.createFile(root,name,fileSize,allocationTechnique , disk,-1,null);
            }
            else{
                System.out.println("you don't have accsess ");
            }
        }
        
        else if(command.equalsIgnoreCase("CreateFolder")){
            String name=input.next();
             String acc=manger.chcekAcc(root, currUser, name);
                 if(acc.equals("11")||acc.equals("10")||currUser.getType().equals("admin")){
               manger.creatFolder(root, name);
            }
            else{
                System.out.println("you don't have accsess ");
            }
         
        }
        
        else if(command.equalsIgnoreCase("DeleteFile")){
            
            String name=input.next();
            String acc=manger.chcekAcc(root, currUser, name);
                   if(acc.equals("11")||acc.equals("01")||currUser.getType().equals("admin")){
                manger.DeleteFile(root, name, disk, allocationTechnique);
            }
            else{
                System.out.println("you don't have accsess ");
            }
        
        
        }
        
        else if(command.equalsIgnoreCase("DeleteFolder")){
          
            String name=input.next();
              String acc=manger.chcekAcc(root, currUser, name);
                   if(acc.equals("11")||acc.equals("01")||currUser.getType().equals("admin")){
                manger.DeleteFolder(root, name,disk,allocationTechnique);
            }
            else{
                System.out.println("you don't have accsess ");
            }
           
        
        }


        else if(command.equalsIgnoreCase("DisplayDiskStatus")){
            
            manger.DisplayDiskStatus(disk);
          
        }

        else if(command.equalsIgnoreCase("DisplayDiskStructure")){
        
            root.printDirectoryStructure(root,"");
        
        }
        else if(command.equalsIgnoreCase("TellUser")){
        
            System.out.println(currUser.getName());
        
        
        }
        
        else if(command.equalsIgnoreCase("CreateUser")){
            if(currUser.getType().equals("admin")){
        
            String userName=input.next();                    
            String password=input.next();

            if(manger.createUser(users, userName, password)){
               System.out.println("User Created ");
               
            }
            
            else{System.out.println("User Already Exists");}
            }
            else{System.out.println("you don't have access ");}
            
        }
        else if(command.equalsIgnoreCase("Grant")){
            
            if(currUser.getType().equals("admin")){
            String userName=input.next();           
            String path=input.next();               
            String accsess=input.next();
                        
            int count=-1;
            for(int i=0;i<users.size();i++){
            if(userName.equals(users.get(i).getName())){
                  count=i;
                  break;
              }
              }
        
            if(count>-1){
                
                manger.giveAccsess(root, path, users.get(count), accsess);  
                  System.out.println("User take access");
            }
            else{
                System.out.println("User not Exists");

                }             

            }
             else{System.out.println("you don't have access ");}
            
        }        
        
        else if(command.equalsIgnoreCase("Login")){
            String userName=input.next();
           
            String password=input.next();
            int f=0;
            for(int i=0;i<users.size();i++){
            if(userName.equals(users.get(i).getName())&&password.endsWith(users.get(i).getPassword())){
             f=1;
              currUser=users.get(i);
        }
        }
        if(f==0){System.out.println("Invalid username or password ");}
        else{System.out.println("login success ");}
        
        }         
        else if(command.equalsIgnoreCase("Exsit")){
           
            //save to file  
            FileWriter myWriter = new FileWriter("save.txt");
            FileWriter myUsers = new FileWriter("users.txt");
            FileWriter userAccess = new FileWriter("access.txt");            
            
            manger.saveUsers(myUsers, users);
            allocationTechnique.writeIntoFile(root,"",myWriter);
            manger.saveAccess(root, userAccess, "");
            
            myWriter.close();
            myUsers.close();
            userAccess.close();
            break;
         }
        
            root.printDirectoryStructure(root,"");
        
        }
        
        
        
        
        System.out.println(" ");
    }
}





/*
CreateFile root/file.txt 100
CreateFolder root/folder1
DeleteFile root/folder1/file.txt  
DeleteFolder root/folder1	
DisplayDiskStatus
DisplayDiskStructure

*/