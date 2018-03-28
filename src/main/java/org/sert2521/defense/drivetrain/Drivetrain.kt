package org.sert2521.defense.drivetrain

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.sert2521.defense.util.LEFT_FRONT_MOTOR
import org.sert2521.defense.util.LEFT_REAR_MOTOR
import org.sert2521.defense.util.RIGHT_FRONT_MOTOR
import org.sert2521.defense.util.RIGHT_REAR_MOTOR
import org.sert2521.defense.util.leftJoystick
import org.sertain.command.Subsystem
import org.sertain.hardware.*

object Drivetrain : Subsystem() {
    private val leftDrive = Talon(LEFT_FRONT_MOTOR).autoBreak() + Talon(LEFT_REAR_MOTOR).autoBreak()
    private val rightDrive = Talon(RIGHT_FRONT_MOTOR).autoBreak() + Talon(RIGHT_REAR_MOTOR).autoBreak()

    private val drive = DifferentialDrive(leftDrive, rightDrive)

    override val defaultCommand = ArcadeDrive()

    val leftPosition get() = leftDrive.getEncoderPosition()
    val rightPosition get() = rightDrive.getEncoderPosition()

    override fun onCreate() {
        leftDrive.setSelectedSensor(FeedbackDevice.QuadEncoder)
        rightDrive.setSelectedSensor(FeedbackDevice.QuadEncoder)
    }

    override fun onStart() {
        leftDrive.setEncoderPosition(0)
        rightDrive.setEncoderPosition(0)
    }

    override fun execute() {
        SmartDashboard.putNumber("Drivetrain Left Position", leftPosition.toDouble())
        SmartDashboard.putNumber("Drivetrain Right Position", rightPosition.toDouble())
    }

    fun arcade() {
        drive.arcadeDrive(leftJoystick.x, leftJoystick.y)
    }

    fun stop() {
        drive.stopMotor()
    }
}
