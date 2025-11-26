package org.codeWithGA.FuzzyLogic.inference;

import java.util.Map;
import java.util.List;
public class EvaluationResult {
        public final Map<String, Map<String, Double>> fuzzifiedInputs;
        public final List<RuleFiring> ruleFirings;
        public final double[] sampledX;
        public final double[] sampledMu;
        public final double crispOutput;

        public EvaluationResult(Map<String, Map<String, Double>> fuzzifiedInputs,
                                List<RuleFiring> ruleFirings,
                                double[] sampledX,
                                double[] sampledMu,
                                double crispOutput) {
            this.fuzzifiedInputs = fuzzifiedInputs;
            this.ruleFirings = ruleFirings;
            this.sampledX = sampledX;
            this.sampledMu = sampledMu;
            this.crispOutput = crispOutput;
        }

        public static class RuleFiring {
            public final String ruleId;
            public final double strength;
            public RuleFiring(String ruleId, double strength) { this.ruleId = ruleId; this.strength = strength; }
        }
}
