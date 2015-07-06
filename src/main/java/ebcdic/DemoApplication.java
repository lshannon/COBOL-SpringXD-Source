package ebcdic;

import net.sf.JRecord.Details.AbstractLine;
import net.sf.JRecord.Details.LayoutDetail;
import net.sf.JRecord.External.CopybookLoader;
import net.sf.JRecord.External.RecordEditorXmlLoader;
import net.sf.JRecord.IO.AbstractLineReader;
import net.sf.JRecord.IO.LineIOProvider;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		String dataFile = new ClassPathResource("/data/SB950.BLPRIC.BL376T2.D1511700").getFile().getAbsolutePath();
//		String copybookFile = new ClassPathResource("/definitions/BL347_Record_type_030_-_Account_Data.txt").getFile().getAbsolutePath();
//		String copybookFile = new ClassPathResource("/definitions/BL347_Record_type_140_-_Loan_Data.txt").getFile().getAbsolutePath();
		String copybookFile = new ClassPathResource("/definitions/accountData.xml").getFile().getAbsolutePath();
//		String copybookFile = new ClassPathResource("/definitions/loanData.xml").getFile().getAbsolutePath();
		System.out.println("dataFile = " + dataFile);
		System.out.println("copybookFile = " + copybookFile);

		CopybookLoader loader = new RecordEditorXmlLoader();
		LayoutDetail layout = loader.loadCopyBook(copybookFile, 0, 0, "", 0, 0, null).asLayoutDetail();

		int fileStructure = layout.getFileStructure();

		AbstractLineReader reader  = LineIOProvider.getInstance().getLineReader(fileStructure);

		reader.open(dataFile, layout);

		AbstractLine record = reader.read();

		System.out.println(record.getFullLine());

		while(record != null) {
			System.out.println("The record is " + record.getData().length + " bytes long");
			record = reader.read();
		}

//        SpringApplication.run(DemoApplication.class, args);
	}
}
