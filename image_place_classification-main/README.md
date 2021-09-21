# image_place_classification

Required Data : image, image_tag, image_coordinate

Step 1 geo-clustering : Using meanshift algorithm to cluster images by coordiantes.

Step 2 visual-clustering : Using bundler to get image similarity, and make sub-clusters(sub-grpah) by image similarity.

Step 3 tag-clustering : Using gensim to get similarity between image_tag and place, and classify image by similarity.

As a result, [photo-name].jpg -> [place-label]_[geo-label]_[visual-label]_[photo-name].jpg
