package org.firstinspires.ftc.teamcode.learnJavaForFTC;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/*
-------- Operators --------
Math Operators
    = - assignment operator
    + - addition operator
    - - subtraction operator AND negative operator (So saying -x is the same thing
    as saying (0 - x))
    * - multiplication operator
    / - division operator - be aware that if you are using integers only the whole part is kept.
    It is NOT rounded. For example: 5 / 2 == 2 ( == is how to describe that 2 things are equal)
    % - modulo operator - This gives the remainder. For example: 5 % 2 == 1
    ( and ) - These are parenthesis and they allow you to specify the order of operations just
    like in regular math. You can use these to tell the difference between
    3 * (4 + 2) or (3 * 4) + 2. While there is a well defined order of operations, instead of
    memorizing that it makes sense to use parenthesis to be specific
More operators that combine a math operator and an assignment operator. Some of the common ones
    ++ - increment. For example: x++ means the same as x = x + 1
    -- - decrement. For example: x-- means the same as x = x - 1
    += - add and assignment. For example x += 2 means the same as x = x + 2
    *= - multiply and assignment. For example x *= 2 means the same as x = x * 2
    /= - divide and assignment. For example x /= 2 means the same as x = x / 2
    %= - modulo and assignment. For example x %= 2 means the same as x = x % 2
More conditional operators
    == - is equal to
    != - is not equal to
    < - is less than
    > - is greater than
    <= - is less than on equal to
    >= - is greater than or equal to
More logical operators
    && - logical AND (return true if condition A AND condition B are true,
    otherwise return false). For example: (A < 10) && (B > 5)
    || - logical OR (return true if condition A OR condition B is true, otherwise
    return false). For example: (A < 10) || (B > 5)
    ! - logical NOT (return true if condition A is false, otherwise return false)
    For example: !(A < 10)
    ^ - XOR (return true if either A or B is true but if they both are then return false).
    This is rarely used.  For example: (A < 10) ^ (B > 5)

 */
public class MathOpMode extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        double speedForward = -gamepad1.left_stick_y / 2.0;

        telemetry.addData("Left Stick y", gamepad1.left_stick_y);
        telemetry.addData("speed Forward", speedForward);
    }
}
