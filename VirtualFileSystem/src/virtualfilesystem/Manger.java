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
public class Manger {
    
     private Alocation_techniques techniq;
     private Directory palace;
    public Alocation_techniques getTechniq() {
        return techniq;
    }

    public void setTechniq(Alocation_techniques techniq) {
        this.techniq = techniq;
    }
     
    public void createFile(Directory curr,String path,int size ,Alocation_techniques techniq,int disk[],int startFile,int arr[]){
      
    //check file find or no
    if(checkFile(curr,path)){ //not find
        //allocation new file
        int index = 0;
       
        for(int i=0;i<path.length();i++){
       if(path.charAt(i)=='/'){index=i;}
       }
       path=path.substring(index+1,path.length());  
    File newOne = techniq.Allocation(disk, path, size,startFile,arr);
    
    if(newOne==null){
        
    System.out.println("disk capacity is low ");
    
    }
    else{
    this.palace.getFiles().add(newOne);
    
        System.out.println("file created");
    
    }}
    else{
    
        System.out.println("fiald to create file");
    }
    
    
    }
    

public boolean checkFile(Directory curr,String name){
   //root/folder/file.txt
   
    int find=name.indexOf('/');
    if(find!=-1){
    String temp=name.substring(0,find);
    if(temp.equals("root")){
            name=name.substring(find+1, name.length()); 

    }
    }
    
   int countBack=0;
   int count=0;
   for(int i=0;i<name.length();i++){
   
   if(name.charAt(i)=='/'){countBack++;}
   }
   
   
   if(countBack==0){
      this.palace=curr;
   for(int i=0;i<curr.getFiles().size();i++){

    if(name.equals(curr.getFiles().get(i).getFilePath())){
    count++;
    }
  }
   
   if(count>0){return  false;}
   }
   
   else{
       Directory newOne=null;
       find=name.indexOf('/');
      String folder=name.substring(0, find);
      name=name.substring(find+1, name.length());
       
      for(int i=0;i<curr.getSubDirectories().size();i++){
      
      if(folder.equals(curr.getSubDirectories().get(i).getDirectoryPath())){
         newOne=curr.getSubDirectories().get(i);
         break;
      }
      }
      
      if(newOne!=null){
       return checkFile(newOne, name);
      }
      else{return false;}
   }
   

return  true;

}


public void creatFolder(Directory curr,String path){

    // root/zyad/mama/nana
    //check file find or no
    if(checkFolder(curr,path)){ //not find
        //allocation new file
       int index = 0;
       for(int i=0;i<path.length();i++){
       if(path.charAt(i)=='/'){index=i;}
       }
       path=path.substring(index+1,path.length());  
   
      Directory newOne= new Directory();
      newOne.setDirectoryPath(path);
      this.palace.getSubDirectories().add(newOne);
    
        System.out.println("folder created");
    }
    
    
    else{
    
        System.out.println("fiald to create folder");
    }
    

}


public boolean checkFolder(Directory curr,String name){

    //root/zyad/amin
 
    int find=name.indexOf('/');
    if(find!=-1){
    String temp=name.substring(0,find);
    if(temp.equals("root")){
            name=name.substring(find+1, name.length()); 

    }
    }
    
   int countBack=0;
   int count=0;
   for(int i=0;i<name.length();i++){
   
   if(name.charAt(i)=='/'){countBack++;}
   }
   
   
   if(countBack==0){
      this.palace=curr;
   for(int i=0;i<curr.getSubDirectories().size();i++){

    if(name.equals(curr.getSubDirectories().get(i).getDirectoryPath())){
    count++;
    }
  }
   
   if(count>0){return  false;}
   }
   
   else{
       Directory newOne=null;
       find=name.indexOf('/');
      String folder=name.substring(0, find);
      name=name.substring(find+1, name.length());
      for(int i=0;i<curr.getSubDirectories().size();i++){
      
      if(folder.equals(curr.getSubDirectories().get(i).getDirectoryPath())){
         newOne=curr.getSubDirectories().get(i);
         break;
      }
      }
      
      if(newOne!=null){
         return checkFolder(newOne, name);
      }
      else{return false;}
   }
   

return  true;




}

public void DeleteFile(Directory curr,String path,int disk[],Alocation_techniques techniq){
  if(!checkFile(curr,path)){
       int index = 0;
       for(int i=0;i<path.length();i++){
       if(path.charAt(i)=='/'){index=i;}
       }
       path=path.substring(index+1,path.length());  
   
      for(int i=0;i<palace.getFiles().size();i++){
      if(this.palace.getFiles().get(i).getFilePath().equals(path)){
      techniq.DeAllocation(this.palace.getFiles().get(i), disk);
       this.palace.getFiles().remove(i);
      }
              }
         System.out.println("file deleted");

  
  }
  else{
      System.out.println(" the file not found");
  }


}
public void DeleteFolder(Directory curr,String path,int disk[],Alocation_techniques techniq){
  if(!checkFolder(curr,path)){
       int remove=0;
       int index = 0;
       for(int i=0;i<path.length();i++){
       if(path.charAt(i)=='/'){index=i;}
       }
       String comp=path.substring(index+1,path.length());  
      
        for(int i=0;i<palace.getSubDirectories().size();i++){
        
            if(palace.getSubDirectories().get(i).getDirectoryPath().equals(comp)){
            palace=palace.getSubDirectories().get(i);
            remove=i;
            }
        }
      
  for(int i=0;i<palace.getFiles().size();i++){
  String newOne=palace.getFiles().get(i).getFilePath();
  String data =path+"/"+newOne;
  DeleteFile(curr, data, disk, techniq);
  }
  
  for(int i=0;i<palace.getSubDirectories().size();i++){
   String newOne=palace.getSubDirectories().get(i).getDirectoryPath();
   String data =path+"/"+newOne;
   DeleteFolder(curr, data, disk, techniq);
  }
  
      checkFolder(curr, path);
      
      palace.getSubDirectories().remove(remove);
      
      
      System.out.println("folder deleted");
  
  }
  else{System.out.println("folder is not found");}
}

public void DisplayDiskStatus(int disk[]){


            ArrayList<Integer>empty=new ArrayList<>();
            ArrayList<Integer>Allocated=new ArrayList<>();

            for(int i=0;i<disk.length;i++){
                if(disk[i]==0){empty.add(i);}
                else{Allocated.add(i);}
            }
        
        
            System.out.println("Empty space: " +empty.size());
            System.out.println("Allocated  space: " +Allocated.size());
 
            System.out.println("Empty Blocks in the Disk");
            
            for(int i=0;i<empty.size();i++){
              System.out.print(empty.get(i)+" ");
            }  
            
            System.out.println(" ");
        
            System.out.println("Allocated  Blocks in the Disk");
            for(int i=0;i<Allocated.size();i++){
                System.out.print(Allocated.get(i)+" ");
            }  
                System.out.println(" ");




}


public void giveAccsess(Directory root,String path,User user,String accsess){
//root/manar
    Directory newOne = null;
    if(!checkFolder(root, path)){
       int index = 0;
       for(int i=0;i<path.length();i++){
       if(path.charAt(i)=='/'){index=i;}
       }
       path=path.substring(index+1,path.length());  
 
for(int i=0;i<palace.getSubDirectories().size();i++){

if(path.equals(palace.getSubDirectories().get(i).getDirectoryPath())){

newOne=palace.getSubDirectories().get(i);

}

}

newOne.getUsers().add(user);
newOne.getAccsess().add(accsess);


}
    
}


public String chcekAcc(Directory curr,User user,String name){
    //root/ 
       String accsess="00";
    int find=name.indexOf('/');
    if(find!=-1){
    String temp=name.substring(0,find);
    if(temp.equals("root")){
            name=name.substring(find+1, name.length()); 

    }
    }
    
   int countBack=0;
   for(int i=0;i<name.length();i++){
   
   if(name.charAt(i)=='/'){countBack++;}
   }
   
   
   if(countBack==0){
       Directory newOne = null;
   for(int i=0;i<curr.getSubDirectories().size();i++){

    if(name.equals(curr.getSubDirectories().get(i).getDirectoryPath())){
      newOne=curr.getSubDirectories().get(i);
    }
  }
   if(newOne!=null){

   for(int i=0;i<newOne.getUsers().size();i++){
   
   if(newOne.getUsers().get(i)==user){
   
       accsess=newOne.getAccsess().get(i);
   
   }
   
   }
   }
   
   return accsess;
   }
   
   else{
       //root/zyad/ana
      //manar/za.txt
      
       Directory newOne=null;
       find=name.indexOf('/');
      String folder=name.substring(0, find);
      name=name.substring(find+1, name.length());
      for(int i=0;i<curr.getSubDirectories().size();i++){
      
      if(folder.equals(curr.getSubDirectories().get(i).getDirectoryPath())){
         newOne=curr.getSubDirectories().get(i);
         break;
      }
      }
      
      
   for(int i=0;i<newOne.getUsers().size();i++){
   
   if(newOne.getUsers().get(i)==user){
   
       accsess=newOne.getAccsess().get(i);
   
   }
   
   }
      if(accsess.equals("00")){
         return chcekAcc(newOne,user, name);
      }
   }
   

return  accsess;


}


public boolean createUser(ArrayList<User>users,String userName,String password){


            int count=0;
            for(int i=0;i<users.size();i++){
            if(userName.equals(users.get(i).getName())){
                  count++;
                  break;
              }
              }
        
            if(count>0){return false;}
            else{
                    User newUser= new User(userName, password, "user");
                    users.add(newUser);
                    return true;
                  }            
        




}

 public void saveUsers(FileWriter myWriter,ArrayList<User>users) throws IOException{


     for(int i=1;i<users.size();i++){
    
        myWriter.write(users.get(i).getName()+" "+users.get(i).getPassword()+" "+users.get(i).getType()+"\n");
      
     }

}

 
 public void loadUsers(FileReader myFile,ArrayList<User>users){
             Scanner file = new Scanner(myFile);
     
     while(file.hasNext()){
     
         String username=file.next();
         String password=file.next();
         String type=file.next();
         User newUser= new User(username, password, type);
         
         users.add(newUser);
     }
 }
 //root
 //root/zyad
 //root/zyad/manar
 
 
 public  void saveAccess(Directory root,FileWriter myWriter,String name) throws IOException{
     
   if(root.getAccsess().size()>0){  
        
       myWriter.write(name+root.getDirectoryPath()+"\n");
        
        myWriter.write(root.getAccsess().size()+"\n");
        for(int i=0;i<root.getUsers().size();i++){
   
            myWriter.write(root.getUsers().get(i).getName()+" "+root.getAccsess().get(i)+"\n");
   
        }
 
   }
           name+=root.getDirectoryPath();  

        for(int i=0;i<root.getSubDirectories().size();i++){
   
            saveAccess(root.getSubDirectories().get(i), myWriter,name+"/");
   
        }
 
    
 }
 
 public void loadAccess(FileReader myFile,Directory root,ArrayList<User>users){
    
    Scanner file = new Scanner(myFile);
    while(file.hasNext()){
        
        String path=file.next();
        int number=file.nextInt();
        for(int i=0;i<number;i++){
            
            String user=file.next();
            String access=file.next();
          int find=0;
         for(int j=0;j<users.size();j++){
             if(user.equals(users.get(j).getName())){
                find=j;
             }
         
         }    
          giveAccsess(root,path,users.get(find), access);
        
        
        }
     
     }             
     
 
 
 
 
 
 } 
}




