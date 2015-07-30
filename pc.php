<?php

require 'jdf.php';

class PersianDate
{
	const C128 = 128 * 365 + 31;
	
	private $Leap128 = [0, 4, 8, 12, 16, 20, 24, 28, 33, 37, 41, 45, 49, 53, 57, 61, 66, 70, 74, 78, 82, 86, 90, 95, 99, 103, 107, 111, 115, 119, 123];
	private $date = '';
	private $year = 0;
	private $month = 0;
	private $day = 0;
	
	public function __construct($date = null)
	{
		if ($date == null) {
			$date = date('Y-m-d');
		}
		$this->date = $date;
		$this->compute();
	}
	
	private function days()
	{
		$t0 = (new DateTime('622-3-21'))->getTimestamp(); // 1-1-1
		$t1 = (new DateTime($this->date))->getTimestamp();
		return floor(($t1 - $t0) / 3600 / 24);
	}
	
	public function allDaysOfYear($y = -1)
	{
		$y = ($y < 0) ? $this->$year : $y;
		return $this->isLeap($y) ? 366 : 365;
	}
	
	public function daysOfMonths($y = -1)
	{
		$y = ($y < 0) ? $this->$year : $y;
		$months = [31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29];
		if ($this->isLeap($y))
			$months[11] = 30;
		return $months;
	}
	
	public function isLeap($y = -1) 
	{
		$y = ($y < 0) ? $this->$year : $y;
		$y %= 128;
		return in_array($y, $this->Leap128);
	}
	
	private function compute()
	{
		$days = $this->days();
		$y = 128 * floor($days / self::C128);
		$days %= self::C128;

		$dy = $this->allDaysOfYear($y + 1);
		
		while ($days >= $dy) {
			$days -= $dy;
			$y++;
			$dy = $this->allDaysOfYear($y + 1);
		}

		$daysOfMonths = $this->daysOfMonths($y);
		
		$month = 0;
		
		for ($i = 0; $i < 12; $i++) {
			if ($days > $daysOfMonths[$i]) {
				$days -= $daysOfMonths[$i];
				$month++;
			}
		} 
	
		$this->year = $y + 1;
		$this->month = $month + 1;
		$this->day = $days + 1;
	}
	
	public function __toString()
	{
		return "{$this->year}/{$this->month}/{$this->day}";
	}
}
/*
function isLeap($y) 
{

}
*/
$t = time();
for ($y = 622; $y < 700; $y++) {
	$m = rand(1, 12);
	$d = rand(1, 28);
	$g = gregorian_to_jalali($y, $m, $d, '/');
	$p = new PersianDate("$y-$m-$d");
	if ($p->__toString() !== $g)
	print "($y/$m/$d)\t$p\t$g\n";
}/*
for ($y = 621; $y < 700; $y++) {
	$p = new PersianDate("$y-3-21");
	print "$p\n";
}*/


print "\n-------------------------\nTime = ". (time() - $t) . "\n";
