package gr.bitsplease.bitsplease.iohelper;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.JobOffer;
import gr.bitsplease.bitsplease.models.JobOfferSkills;
import gr.bitsplease.bitsplease.models.Skills;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;



public class Helper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERS = {"First Name", "Last Name", "Address", "Region", "Education Level", "Level", "Skills", "Skills", "Skills"};

    static String SHEET = "Applicants";
    static String SHEET1= "Skills";
    static String SHEET2= "JobOffers";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Applicant> excelToApplicants(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Applicant> applicants = new ArrayList<Applicant>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Applicant applicant = new Applicant();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            applicant.setFirstName((String) currentCell.getStringCellValue());
                            break;

                        case 1:
                            applicant.setLastName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            applicant.setAddress(currentCell.getStringCellValue());
                            break;

                        case 3:
                            applicant.setRegion(currentCell.getStringCellValue());
                            break;

                        case 4:
                            applicant.setEdLevel(currentCell.getStringCellValue());
                            break;

                        case 5:
                            applicant.setLevel(currentCell.getStringCellValue());
                            break;

                        case 6:

                            applicant.setSkills((String) currentCell.getStringCellValue());

                            break;
                    }

                    cellIdx++;
                }

                applicants.add(applicant);
            }

            workbook.close();

            return applicants;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Skills> excelToSkills(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET1);
            Iterator<Row> rows = sheet.iterator();

            List<Skills> skills = new ArrayList<Skills>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Skills skill = new Skills();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            skill.setName((String) currentCell.getStringCellValue());
                            break;



                    }

                    cellIdx++;
                }

                skills.add(skill);
            }

            workbook.close();

            return skills;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<JobOffer> excelToJobOffers(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET2);
            Iterator<Row> rows = sheet.iterator();

            List<JobOffer> jobOffers = new ArrayList<JobOffer>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                JobOffer jobOffer = new JobOffer();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            jobOffer.setCompanyName((String) currentCell.getStringCellValue());
                            break;

                        case 1:
                            jobOffer.setTitleOfPosition((String) currentCell.getStringCellValue());
                            break;

                        case 2:
                            jobOffer.setRegion((String) currentCell.getStringCellValue());
                            break;

                        case 3:
                            jobOffer.setEdLevel((String) currentCell.getStringCellValue());
                            break;

//                        case 4:
//                            jobOffer.setJobOfferSkills((List) currentCell.getArrayFormulaRange());
//                            break;
                    }

                    cellIdx++;
                }

                jobOffers.add(jobOffer);
            }

            workbook.close();

            return jobOffers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}

