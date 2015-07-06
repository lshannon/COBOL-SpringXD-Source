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
		String copybookFile = new ClassPathResource("/definitions/accountData.xml").getFile().getAbsolutePath();
		System.out.println("dataFile = " + dataFile);
		System.out.println("copybookFile = " + copybookFile);

		CopybookLoader loader = new RecordEditorXmlLoader();
		LayoutDetail layout = loader.loadCopyBook(copybookFile, 0, 0, "", 0, 0, null).asLayoutDetail();

		int fileStructure = layout.getFileStructure();

		AbstractLineReader reader  = LineIOProvider.getInstance().getLineReader(fileStructure);

		reader.open(dataFile, layout);

		AbstractLine record = reader.read();

		System.out.println(record.getFullLine());
		int recordCount = 0;

		while(record != null && recordCount < 100) {
			recordCount ++;
			int length = record.getData().length;

			System.out.println("recordLength = " + length);

			System.out.println("******** ACCOUNT RECORD *************");
			System.out.println("\tlanguage: " + record.getFieldValue("BLMASTER-LANGUAGE"));
			System.out.println("\tres-code: " + record.getFieldValue("BLMASTER-ACCNT-RES-CODE"));
			System.out.println("\tcurrency: " + record.getFieldValue("BLMASTER-CURRENCY"));
			System.out.println("\tsoc-ins-no: " + record.getFieldValue("BLMASTER-SOC-INS-NO"));
			System.out.println("\tnext-act-date: " + record.getFieldValue("BLMASTER-NEXT-ACT-DATE"));
			System.out.println("\tsnsav: " + record.getFieldValue("BLMASTER-SNSAV"));
			System.out.println("\tstr-codes: " + record.getFieldValue("BLMASTER-STR-CODES"));
			System.out.println("\trec-length: " + record.getFieldValue("BLMASTER-NA-REC-LENGTH"));
			System.out.println("\tseg-ind: " + record.getFieldValue("BLMASTER-NA-SEG-IND"));
			System.out.println("\tdata-segments: " + record.getFieldValue("BLMASTER-NA-DATA-SEGMENTS"));
			System.out.println("******** LOAN RECORD *************");
			System.out.println("\trec-length: " + record.getFieldValue("BLMASTER-REC-LENGTH"));
			System.out.println("\tloan-number: " + record.getFieldValue("BLMASTER-LOAN-NUMBER"));
			System.out.println("\tnext-act-date: " + record.getFieldValue("BLMASTER-NEXT-ACT-DATE"));
			System.out.println("\tfunction-code: " + record.getFieldValue("BLMASTER-FUNCTION-CODE"));
			System.out.println("\tshort-name: " + record.getFieldValue("BLMASTER-SHORT-NAME"));
			System.out.println("\tloan-sub-type: " + record.getFieldValue("BLMASTER-LOAN-SUB-TYPE"));
			System.out.println("\tloan-class: " + record.getFieldValue("BLMASTER-LOAN-CLASS"));
			System.out.println("\tloan-status: " + record.getFieldValue("BLMASTER-LOAN-STATUS"));
			System.out.println("\tissue-date: " + record.getFieldValue("BLMASTER-ISSUE-DATE"));
			System.out.println("\tproceeds-amt: " + record.getFieldValue("BLMASTER-PROCEEDS-AMT"));

			record = reader.read();
		}

		reader.close();
	}
}
