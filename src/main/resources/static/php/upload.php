<?php

// 업로드한 파일을 저장할 디렉토리 경로
$uploadDir = $_SERVER['DOCUMENT_ROOT'] . '/static/upload/';

$targetDir = "/static/upload/";
$rootPath = $_SERVER['DOCUMENT_ROOT'];
$savePath = $rootPath . $targetDir;


// 전송된 파일이 존재하는지 확인
if (isset($_FILES['file']) && $_FILES['file']['error'] === UPLOAD_ERR_OK) {
  // 파일 이름과 확장자
  $fileName = $_FILES['file']['name'];
  $fileExt = pathinfo($fileName, PATHINFO_EXTENSION);

  // 새로운 파일 이름 생성 (현재시간을 기준으로)
  $newFileName = md5(time() . $fileName) . '.' . $fileExt;

  // 파일을 업로드할 경로
  $uploadPath = $uploadDir . $newFileName;

  // 파일을 업로드
  move_uploaded_file($_FILES['file']['tmp_name'], $uploadPath);

  // 저장된 파일 경로 반환
  $response = new stdClass();
  $response->url = $uploadPath;
  
  echo json_encode($response);
}

?>
