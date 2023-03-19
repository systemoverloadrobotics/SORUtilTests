package frc.sorutil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class SorMathTest {
  @Test
  public void polarTest() {
    double[] polarified = SorMath.cartesianToPolar(2, 8);

    double expectedR = 2 * Math.sqrt(17);
    double expectedTheta = Math.toDegrees(Math.atan(4));

    assertTrue(SorMath.epsilonEquals(expectedR, polarified[0]), "Magnitude result from cartesianToPolar is not equal");
    assertTrue(SorMath.epsilonEquals(expectedTheta, polarified[1]), "Angle result from cartesianToPolar is not equal");

    polarified = SorMath.cartesianToPolar(-2, -4);

    expectedR = 2 * Math.sqrt(5);
    expectedTheta = Math.toDegrees(Math.atan(2) + Math.PI);

    assertTrue(SorMath.epsilonEquals(expectedR, polarified[0]), "Magnitude result from cartesianToPolar is not equal");
    assertTrue(SorMath.epsilonEquals(expectedTheta - 360, polarified[1]), "Angle result from cartesianToPolar is not equal");
  }

  @Test
  public void linearInterpolateTest() {
    double result = SorMath.linearInterpolate(20, 10, 30, 100, 300);

    assertTrue(SorMath.epsilonEquals(result, 200), "Result from interpolate is approximately equal to expected");

    result = SorMath.linearInterpolate(-10, 10, 30, 100, 300);
    assertTrue(SorMath.epsilonEquals(result, -100),
        "Result from interpolate is approximately equal to expected when extrapolated negative");

    boolean caught = false;
    try {
      result = SorMath.linearInterpolate(-10, 10, 10, 100, 300);
    } catch (IllegalArgumentException e) {
      caught = true;
    }
    if (!caught) {
      fail("linearInterpolate should throw an exception when the scale arguments are zero!");
    }

    result = SorMath.linearInterpolate(20, 10, 30, 100, 100);
    assertTrue(SorMath.epsilonEquals(result, 100),
        "Result from interpolate is approximately equal to expected when low == high");

  }

  @Test 
  public void circleSnappingTest() {
    int snapped = SorMath.circleSnappingDegrees(36, 8);
    assertEquals(45, snapped, "Expected a circle with 8 divisions (45 degrees) to snap 36 degrees to 45");

    snapped = SorMath.circleSnappingDegrees(-38, 10);
    assertEquals(324, snapped, "Expected a circle with 10 divisions (36 degrees) to snap -38 degrees to 324");
  }
}
