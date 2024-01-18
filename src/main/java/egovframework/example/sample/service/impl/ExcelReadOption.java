package egovframework.example.sample.service.impl;


import java.util.ArrayList;
import java.util.List;
 
public class ExcelReadOption {
    
    //엑셀파일의 경로
    private String filePath;
    
    //추출할 컬럼 명  
    private List<String> outputColumns;
    
    //추출을 시작할 행 번호
    private int startRow;
 
    public String getFilePath() {
        return filePath;
    }
 
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
 
    public List<String> getOutputColumns() {
 
        List<String> temp = new ArrayList<String>();
        temp.addAll(outputColumns);
 
        return temp;
    }
 
    public void setOutputColumns(List<String> outputColumns) {
 
    	//이걸 A,B,C,D 이런 식으로 추가하던데
        List<String> temp = new ArrayList<String>();
        temp.addAll(outputColumns);
 
        this.outputColumns = temp;
    }
 
    //이게 무슨소리야
    //-> ... : 매개변수를 받긴하지만 몇개인지 모른다라는 뜻(몇개의 매개변수를 넣어도 다 받을 수 있다는 뜻)
    public void setOutputColumns(String ... outputColumns) {
 
        if(this.outputColumns == null) {
            this.outputColumns = new ArrayList<String>();
        }
 
        for(String ouputColumn : outputColumns) {
            this.outputColumns.add(ouputColumn);
        }
    }
 
    public int getStartRow() {
        return startRow;
    }
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
  }
