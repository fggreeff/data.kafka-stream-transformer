package com.github.fggreeff.transformer;

/**
 * This interface represents an abstraction for transformation mechanism which
 * should provide its own {@link Transformer} implementation.
 *
 * @param <S> the type of the source entity to transform.
 * @param <T> the type of the target entity.
 */
public interface Transformer<S, T> {
    /**
     * Transforms source entity of the type S to the target entity of the type T.
     *
     * @param source source object of the type S to transform.
     * @return transformed object of the type T
     * @see TransformationException an exception which could be thrown during transformation.
     */
    T transform(S source);
}


