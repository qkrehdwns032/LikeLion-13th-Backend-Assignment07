package com.likelion.basecode.korea.application;

import com.likelion.basecode.common.client.KoreaSearchClient;
import com.likelion.basecode.common.client.TagRecommendationClient;
import com.likelion.basecode.common.error.ErrorCode;
import com.likelion.basecode.common.exception.BusinessException;
import com.likelion.basecode.korea.api.dto.response.KoreaListResponseDto;
import com.likelion.basecode.korea.api.dto.response.KoreaResponseDto;
import com.likelion.basecode.post.domain.Post;
import com.likelion.basecode.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KoreaService {

    private final PostRepository postRepository;
    private final TagRecommendationClient tagClient;
    private final KoreaSearchClient koreaSearchClient;

    // 전체 목록 조회
    public KoreaListResponseDto fetchAllRecommended() {
        List<KoreaResponseDto> all = koreaSearchClient.fetchAllKoreas();
        return new KoreaListResponseDto(all);
    }

//    // 특정 게시글의 추천 태그를 기반으로 추천
//    public KoreaListResponseDto recommendBooksByPostId(Long postId) {
//        // 1. 게시글 조회
//        Post post = postRepository.findById(postId)
//            .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND_EXCEPTION,
//                ErrorCode.POST_NOT_FOUND_EXCEPTION.getMessage()));
//
//        // 2. AI 기반 태그 추천
//        List<String> tags = tagClient.getRecommendedTags(post.getContents());
//
//        // 3. 태그 추천 결과가 비어있는 경우 예외 처리
//        if (tags.isEmpty()) {
//            throw new BusinessException(ErrorCode.TAG_RECOMMENDATION_EMPTY,
//                ErrorCode.TAG_RECOMMENDATION_EMPTY.getMessage());
//        }
//
//        // 4. 전체 목록 조회
//        List<KoreaResponseDto> all = koreaSearchClient.fetchAllKoreas();
//
//        // 5. alternativeTitle에 태그가 포함된 도서만 필터링
//        List<KoreaResponseDto> filteredBooks = filterBooksByAlternativeTitle(all, tags);
//
//        // 6. 필터링 결과가 비어있으면 예외 처리
//        if (filteredBooks.isEmpty()) {
//            throw new BusinessException(ErrorCode.KOREA_API_NO_RESULT, ErrorCode.KOREA_API_NO_RESULT.getMessage());
//        }
//
//        // 7. 최종 결과 반환
//        return new KoreaListResponseDto(filteredBooks);
//    }
//
//    // 도서 목록에서 alternativeTitle에 태그가 포함된 도서를 필터링
//    private List<KoreaResponseDto> filterBooksByAlternativeTitle(
//        List<KoreaResponseDto> books, List<String> tags
//    ) {
//        return books.stream()
//            // 각 도서(book)에 대해 필터링 조건 적용
//            .filter(book ->
//                // 추천된 태그 중 하나라도 alternativeTitle에 포함되어 있는지 확인
//                tags.stream().anyMatch(tag -> {
//                    // alternativeTitle이 null일 수 있으므로 Optional로 처리하여 빈 문자열로 대체
//                    String altTitle = Optional.ofNullable(book.alternativeTitle()).orElse("");
//                    // alternativeTitle에 현재 태그가 포함되어 있는 경우 true
//                    return altTitle.contains(tag);
//                })
//            )
//            // 최대 3개의 도서만 선택
//            .limit(3)
//            // 결과를 리스트로 변환
//            .toList();
//    }
}
