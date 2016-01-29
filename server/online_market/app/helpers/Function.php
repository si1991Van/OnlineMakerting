<?php
function getRole($roleId) {
	$role = array(
		ADMIN => 'ADMIN',
		EDITOR => 'EDITOR',
	);
	return $role[$roleId];
}

function selectRoleId()
{
	return array(
		'' => '-- Lựa chọn',
		ADMIN => 'ADMIN',
		EDITOR => 'EDITOR',
	);
}
// show 0 for null
function getZero($number = null)
{
	if($number != '') {
		return $number;
	}
	return 0;
}
//get extension from filename
function getExtension($filename = null)
{
	if($filename != '') {
		return pathinfo($filename, PATHINFO_EXTENSION);
	}
	return null;
}
//get filename from filename
function getFilename($filename = null)
{
	if($filename != '') {
		return pathinfo($filename, PATHINFO_FILENAME);
	}
	return null;
}

//Product type
function getProductType($id) {
	$arr = array(
		TYPEVALUE1 => TYPE1,
		TYPEVALUE2 => TYPE2,
	);
	return $arr[$id];
}

function selectProductType()
{
	return array(
		'' => '-- Lựa chọn',
		TYPEVALUE1 => TYPE1,
		TYPEVALUE2 => TYPE2,
	);
}

//Product status
function getProductStatus($id) {
	$arr = array(
		ACTIVE => PRODUCT_ACTIVE,
		INACTIVE => PRODUCT_INACTIVE,
	);
	return $arr[$id];
}

function selectProductStatus()
{
	return array(
		'' => '-- Lựa chọn',
		ACTIVE => PRODUCT_ACTIVE,
		INACTIVE => PRODUCT_INACTIVE,
	);
}

function generateRandomString($length = RANDOMSTRING) {
    $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    $charactersLength = strlen($characters);
    $randomString = '';
    for ($i = 0; $i < $length; $i++) {
        $randomString .= $characters[rand(0, $charactersLength - 1)];
    }
    return $randomString;
}
