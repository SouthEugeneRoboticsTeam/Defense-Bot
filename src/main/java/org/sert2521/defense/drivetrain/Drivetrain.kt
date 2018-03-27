package org.sert2521.defense.drivetrain

import edu.wpi.first.wpilibj.drive.DifferentialDrive
import org.sert2521.defense.util.LEFT_FRONT_MOTOR
import org.sert2521.defense.util.LEFT_REAR_MOTOR
import org.sert2521.defense.util.RIGHT_FRONT_MOTOR
import org.sert2521.defense.util.RIGHT_REAR_MOTOR
import org.sert2521.defense.util.leftJoystick
import org.sertain.command.Subsystem
import org.sertain.hardware.Talon
import org.sertain.hardware.autoBreak
import org.sertain.hardware.inverted
import org.sertain.hardware.plus
import org.sertain.hardware.resetEncoder

object Drivetrain : Subsystem() {
    private val frontLeft = Talon(LEFT_FRONT_MOTOR) + Talon(LEFT_REAR_MOTOR)
    private val frontRight = Talon(RIGHT_FRONT_MOTOR) + Talon(RIGHT_REAR_MOTOR)

    private val drive = DifferentialDrive(frontLeft, frontRight)

    override val defaultCommand = ArcadeDrive()

    init {
        frontLeft.autoBreak()
        frontRight.autoBreak()
    }

    override fun onStart() {
        stop()

        frontLeft.resetEncoder()
        frontRight.resetEncoder()
    }

    fun arcade() {
        drive.arcadeDrive(leftJoystick.x, leftJoystick.y)
    }

    fun stop() {
        drive.stopMotor()
    }
}
