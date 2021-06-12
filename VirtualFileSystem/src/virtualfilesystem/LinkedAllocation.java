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
public class LinkedAllocation implements Alocation_techniques{

    @Override
    public File Allocation(int[] disk, String name, int size,int startFile,int allocated[]) {
        
        File New=null;
        int countZero=0;
        int count=0;
        int arr[]= new int[size];
        
        if(allocated==null){
        //check free space greater than file size
        for(int i=0;i<disk.length;i++){
        
            if(disk[i]==0){
                countZero++;
            } 
        }
        //110101010
        if(size<=countZero){
             
            for(int i=0;i<disk.length;i++)  {
              
                if(disk[i]==0){
                
                  if(size==0){break;}   
                    arr[count++]=i;  
                    disk[i]=1;
                    size--;
                }
       
            }
        }
        }
        else{
            for(int i=0;i<allocated.length;i++)  {
                              
                    arr[count++]=allocated[i];  
                    disk[allocated[i]]=1;
       
            }        
        
        }
        
        New= new File();
        New.setFilePath(name);
        New.setAllocatedBlocks(arr);
        New.setDeleted(false);
        
        
        

   
   return New;
   }

    @Override
    public void DeAllocation(File file, int[] disk) {
       
        
  for(int i=0;i<file.getAllocatedBlocks().length;i++){
        
        disk[file.getAllocatedBlocks()[i]]=0;
        
   
   }
        
        
    }

    @Override
    public void writeIntoFile(Directory curr, String name, FileWriter myWriter) {
           try {
            

        
            myWriter.write(name+curr.getDirectoryPath()+'\n');
        
            name+=curr.getDirectoryPath();
            for(int i=0;i<curr.getFiles().size();i++){
            
                   myWriter.write('-'+name+'/'+curr.getFiles().get(i).getFilePath()+"\n");
                
                for(int j=0;j<curr.getFiles().get(i).getAllocatedBlocks().length;j++){   
                    myWriter.write(curr.getFiles().get(i).getAllocatedBlocks()[j]+" ");
                }     
                
                   myWriter.write("\n");
            
            }
        
            
            for(int i=0;i<curr.getSubDirectories().size();i++){
   
                writeIntoFile(curr.getSubDirectories().get(i) , name+'/', myWriter);
   
            
 
                        
            }
               } catch (IOException ex) {
                   
               }

            

    }

    @Override
    public void loadFromFile(Directory root, FileReader myFile,int disk[]) {
      
        Manger mange= new Manger();
        Scanner file = new Scanner(myFile);

        String newOne = null;
       if(file.hasNext()){
        file.next();
       }
       
        while(file.hasNext()){
            
        String read=file.next();
        
        if(read.charAt(0)=='-'){
        newOne=read.substring(1, read.length());
        ArrayList<Integer> temp= new ArrayList<> ();
        while(file.hasNextInt()){
        int x=file.nextInt();
        temp.add(x);
        }
        
        int indexx[]=new int[temp.size()];
        
        for(int i=0;i<temp.size();i++){
            indexx[i]=temp.get(i);
        }        

        mange.createFile(root, newOne,indexx.length,this,disk,0,indexx);
     
        }
     
        else{
        mange.creatFolder(root, read);      
        
        
    }


        }
    }
    
}
