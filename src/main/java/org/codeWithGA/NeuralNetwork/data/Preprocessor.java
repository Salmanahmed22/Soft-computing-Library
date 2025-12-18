package data;

import java.util.*;

public class Preprocessor {

    public static DataSet removeInvalid(DataSet data) {
        List<double[]> cleanX = new ArrayList<>();
        List<Double> cleanY = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            double[] x = data.getFeatures().get(i);
            double y = data.getLabels().get(i);

            if (isValid(x) && !Double.isNaN(y)) {
                cleanX.add(x);
                cleanY.add(y);
            }
        }

        return new DataSet(cleanX, cleanY);
    }

    private static boolean isValid(double[] x) {
        for (double v : x) {
            if (Double.isNaN(v) || Double.isInfinite(v))
                return false;
        }
        return true;
    }
}
