import org.testng.annotations.Test;

public class TestDB {
    @Test
    public void test(){
        DBSendRequest.minTimeTestsQueryPrintToLog();
        DBSendRequest.uniqTestsOnProjectQueryPrintToLog();
        DBSendRequest.listTestsStartsOnTimePrintToLog();
        DBSendRequest.countTestsOnBrowserQueryPrintToLog();
    }
}
