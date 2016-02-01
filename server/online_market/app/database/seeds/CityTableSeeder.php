<?php

class CityTableSeeder extends Seeder {

	public function run()
	{
		$array = array(
			'Hà Nội', 'Hải Phòng', 'Hải Dương', 'Hưng Yên', 'Hà Nam', 'Nam Định', 'Thái Bình',
			'Ninh Bình', 'Hà Giang', 'Cao Bằng', 'Lào Cai', 'Bắc Cạn', 'Lạng Sơn', 'Tuyên Quang',
			'Yên Bái', 'Thái Nguyên', 'Phú Thọ', 'Vĩnh Phúc', 'Bắc Giang', 'Bắc Ninh', 'Quảng Ninh',
			'Điện Biên', 'Lai Châu', 'Sơn La', 'Hoà Bình', 'Thanh Hoá', 'Nghệ An', 'Hà Tĩnh', 'Quảng Bình',
			'Quảng Trị', 'Thừa Thiên-Huế', 'Đà Nẵng', 'Quảng Nam', 'Quảng Ngãi', 'Bình Định', 'Phú Yên',
			'Khánh Hoà', 'Kon Tum', 'Gia Lai', 'Đắc Lắc', 'Đắk Nông', 'TP Hồ Chí Minh', 'Lâm Đồng',
			'Ninh Thuận', 'Bình Phước', 'Tây Ninh', 'Bình Dương', 'Đồng Nai', 'Bình Thuận', 'Bà Rịa- Vũng Tàu',
			'Long An', 'Đồng Tháp', 'An Giang', 'Tiền Giang', 'Vĩnh Long', 'Bến Tre', 'Kiên Giang',
			'Cần Thơ', 'Hậu Giang', 'Trà Vinh', 'Sóc Trăng', 'Bạc Liêu', 'Cà Mau'
			);
		foreach ($array as $value) {
			City::create([
				'name'=> $value,
			]);
		}

	}

}