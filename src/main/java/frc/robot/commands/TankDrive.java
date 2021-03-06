/*----------------------------------------------------------------------------
TankDrive.java  
Creator : Tate
date: Jan 7th 2020

Tank drive class file
----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//type: class
public class TankDrive extends Command {
  public TankDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double joystickY = Deadband(Robot.m_oi.getStickY());
    double stickTwist = Deadband(-Robot.m_oi.getStickTwist());

    Robot.driveTrain.arcadeDrive(stickTwist, joystickY);
    //Robot.driveTrain.setRightMotors(joystickY);
  }

  // Make this return true when this Command no longer needs to run execute()
  // called every time after execute
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.setLeftMotors(0);
    Robot.driveTrain.setRightMotors(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }

	double Deadband(double value) {
		/* Upper deadband */
		if (value >= +0.075) 
			return value;
		
		/* Lower deadband */
		if (value <= -0.075)
			return value;
		
		/* Outside deadband */
		return 0;
	}
}
