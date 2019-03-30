package com.assignment.rsupport.noticejava.domain.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface NoticeRepository extends PagingAndSortingRepository<Notice, Long> {

}
