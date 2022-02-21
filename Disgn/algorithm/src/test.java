import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date 2021 年 12 月 27 日
 */
public class test {
    public static void main(String[] args) throws IOException {
<<<<<<< Updated upstream
        String s ="dasdasdasfweqafwefef";
        System.out.println(s.length());

=======
        String s = exR2(3);
        System.out.println(s);
>>>>>>> Stashed changes
    }

    private static String getString() throws IOException {
        BufferedReader br;
        InputStreamReader isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    public static String exR2(int n){

        String s =exR2(n-3)+n+exR2(n-2)+n;
        if (n<=0) {
            return "";
        }
        return  s ;
    }
}
class IdCode{

    private  Integer deleteFlag;
    private LocalDateTime createDate;
    private  String deptId;
    private  String educationUrl;
    private  String idCode;

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getEducationUrl() {
        return educationUrl;
    }

    public void setEducationUrl(String educationUrl) {
        this.educationUrl = educationUrl;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }
}
