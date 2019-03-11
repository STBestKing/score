import org.apache.commons.io.FileUtils;
import org.apache.poi.examples.util.TempFileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        ArrayList<String> arrayList = toArrayByFileReader1("score.txt");
        List<Student> students = process(arrayList);
        students.get(0).setId(students.get(0).getId().substring(1));

        students.sort(Comparator.comparing(Student::getSum));
        for (int i=students.size()-1; i>=0; i--) {
            students.get(students.size()-i-1).setRank(i+1);
        }

        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getSum());
        }

        students.sort(Comparator.comparing(Student::getId));

        System.out.println("排序后");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getId());
        }

        String[] tittle = {"ID", "name", "s1", "s2", "s3", "s4", "s5", "SUM", "Rank"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = null;

        for (int i=0; i<tittle.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(tittle[i]);
        }

        for (int i=1; i<=students.size(); i++) {
            HSSFRow nrow = sheet.createRow(i);
            HSSFCell ncell = nrow.createCell(0);
            ncell.setCellValue(students.get(i-1).getId());
            ncell = nrow.createCell(1);
            ncell.setCellValue(students.get(i-1).getName());
            ncell = nrow.createCell(2);
            ncell.setCellValue(students.get(i-1).getScore_1());
            ncell = nrow.createCell(3);
            ncell.setCellValue(students.get(i-1).getScore_2());
            ncell = nrow.createCell(4);
            ncell.setCellValue(students.get(i-1).getScore_3());
            ncell = nrow.createCell(5);
            ncell.setCellValue(students.get(i-1).getScore_4());
            ncell = nrow.createCell(6);
            ncell.setCellValue(students.get(i-1).getScore_5());
            ncell = nrow.createCell(7);
            ncell.setCellValue(students.get(i-1).getSum());
            ncell = nrow.createCell(8);
            ncell.setCellValue(students.get(i-1).getRank());
        }

        File file = new File("output.xls");
        try {
            file.createNewFile();
            FileOutputStream stream = FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<String> toArrayByFileReader1(String name) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(name), "UTF-8"));
            String str;
            while ((str = bufferedReader.readLine()) != null && !str.isEmpty()) {
                arrayList.add(str);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int length = arrayList.size();
        for (int i = 0; i < length; i++) {
            System.out.println(arrayList.get(i));
        }
        return arrayList;
    }


    public static List<Student> process(ArrayList<String> arrayList) {
        int length = arrayList.size();
        List<Student> students = new ArrayList<>();
        students.clear();
        for (int i = 0; i < length; i++) {
            String string = arrayList.get(i);
            String[] stringArray = string.split(" ");
            int score_1 = Integer.parseInt(stringArray[2]), score_2 = Integer.parseInt(stringArray[3]), score_3 = Integer.parseInt(stringArray[4]), score_4 = Integer.parseInt(stringArray[5]), score_5 = Integer.parseInt(stringArray[6]);
            students.add(new Student(stringArray[0], stringArray[1], score_1, score_2, score_3, score_4, score_5));
        }
        return students;
    }
}
