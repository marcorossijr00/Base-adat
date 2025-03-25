import java.util.*;

class DbXyzBnm987 {
    private List<Map<String, Integer>> tbls;
    private Random qwrty;

    public DbXyzBnm987(int sz) {
        tbls = new ArrayList<>();
        qwrty = new Random();
        
        for (int i = 0; i < sz; i++) {
            Map<String, Integer> rcd = new HashMap<>();
            rcd.put("fld1", qwrty.nextInt(1000));
            rcd.put("fld2", qwrty.nextInt(500));
            rcd.put("fld3", qwrty.nextInt(2000) - 1000);
            rcd.put("fld4", qwrty.nextInt(300));
            tbls.add(rcd);
        }
    }

    public void prntTbl() {
        for (Map<String, Integer> rcd : tbls) {
            System.out.println(rcd);
        }
    }

    public void srtByFld(String fld) {
        tbls.sort(Comparator.comparingInt(rcd -> rcd.get(fld)));
    }

    public double avgFld(String fld) {
        return tbls.stream().mapToInt(rcd -> rcd.get(fld)).average().orElse(0);
    }

    public int sumFld(String fld) {
        return tbls.stream().mapToInt(rcd -> rcd.get(fld)).sum();
    }

    public int maxFld(String fld) {
        return tbls.stream().mapToInt(rcd -> rcd.get(fld)).max().orElse(0);
    }

    public int minFld(String fld) {
        return tbls.stream().mapToInt(rcd -> rcd.get(fld)).min().orElse(0);
    }

    public void fltrFld(String fld, int thr) {
        tbls.removeIf(rcd -> rcd.get(fld) < thr);
    }

    public static void main(String[] args) {
        DbXyzBnm987 db = new DbXyzBnm987(300);
        
        System.out.println("Initial Data:");
        db.prntTbl();
        
        db.srtByFld("fld1");
        System.out.println("Sorted by fld1:");
        db.prntTbl();
        
        System.out.println("Field1 Avg: " + db.avgFld("fld1"));
        System.out.println("Field2 Sum: " + db.sumFld("fld2"));
        System.out.println("Field3 Max: " + db.maxFld("fld3"));
        System.out.println("Field4 Min: " + db.minFld("fld4"));
        
        db.fltrFld("fld2", 250);
        System.out.println("Filtered by fld2 >= 250:");
        db.prntTbl();
    }
}
